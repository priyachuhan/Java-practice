package core;

import java.awt.image.BufferedImage;

public class Steganography {

    // Encode byte array into image pixels (only LSB of each color channel)
    public static BufferedImage encode(BufferedImage image, byte[] message) throws Exception {
        int messageLength = message.length;

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        // Max capacity check (each pixel has 3 channels × 1 bit = 3 bits)
        if (messageLength * 8 + 32 > imgWidth * imgHeight * 3) {
            throw new Exception("Message too long to encode in this image.");
        }

        BufferedImage encodedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);

        int messageBitIndex = 0;

        // First 32 bits = message length (int)
        byte[] lengthBytes = intToBytes(messageLength);

        outerLoop:
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                int rgb = image.getRGB(x, y);

                int[] colors = {
                        (rgb >> 16) & 0xFF,  // Red
                        (rgb >> 8) & 0xFF,   // Green
                        rgb & 0xFF           // Blue
                };

                // Encode length first (32 bits)
                for (int i = 0; i < 3; i++) {
                    if (messageBitIndex < 32) {
                        int bit = getBit(lengthBytes, messageBitIndex);
                        colors[i] = (colors[i] & 0xFE) | bit;
                        messageBitIndex++;
                    }
                }

                // Then encode message bytes
                for (int i = 0; i < 3; i++) {
                    if (messageBitIndex >= 32 && messageBitIndex < 32 + messageLength * 8) {
                        int bitIndex = messageBitIndex - 32;
                        int bit = getBit(message, bitIndex);
                        colors[i] = (colors[i] & 0xFE) | bit;
                        messageBitIndex++;
                    }
                }

                // Rebuild rgb value, keep alpha as is
                int alpha = (rgb >> 24) & 0xFF;
                int newRgb = (alpha << 24) | (colors[0] << 16) | (colors[1] << 8) | colors[2];
                encodedImage.setRGB(x, y, newRgb);

                if (messageBitIndex >= 32 + messageLength * 8) {
                    // Copy remaining pixels unchanged
                    for (int yy = y; yy < imgHeight; yy++) {
                        for (int xx = (yy == y ? x + 1 : 0); xx < imgWidth; xx++) {
                            encodedImage.setRGB(xx, yy, image.getRGB(xx, yy));
                        }
                    }
                    break outerLoop;
                }
            }
        }

        return encodedImage;
    }

    // Decode message bytes from image pixels
    public static byte[] decode(BufferedImage image) throws Exception {
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        byte[] lengthBytes = new byte[4];
        int bitIndex = 0;

        // Read length first (32 bits)
        outerLoop:
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                int rgb = image.getRGB(x, y);

                int[] colors = {
                        (rgb >> 16) & 0xFF,
                        (rgb >> 8) & 0xFF,
                        rgb & 0xFF
                };

                for (int i = 0; i < 3; i++) {
                    if (bitIndex < 32) {
                        setBit(lengthBytes, bitIndex, colors[i] & 1);
                        bitIndex++;
                    } else {
                        break outerLoop;
                    }
                }
            }
        }

        int messageLength = bytesToInt(lengthBytes);

        if (messageLength <= 0 || messageLength > (imgWidth * imgHeight * 3) / 8) {
            throw new Exception("Invalid or corrupted message length.");
        }

        byte[] messageBytes = new byte[messageLength];
        bitIndex = 0;
        int messageBitsToRead = messageLength * 8;
        int totalBitsRead = 0;

        outerLoop:
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                // Skip first pixels used for length (already read)
                int pixelIndex = y * imgWidth + x;
                if (pixelIndex < Math.ceil(32 / 3.0)) {
                    continue;
                }

                int rgb = image.getRGB(x, y);

                int[] colors = {
                        (rgb >> 16) & 0xFF,
                        (rgb >> 8) & 0xFF,
                        rgb & 0xFF
                };

                for (int i = 0; i < 3; i++) {
                    if (totalBitsRead < messageBitsToRead) {
                        setBit(messageBytes, totalBitsRead, colors[i] & 1);
                        totalBitsRead++;
                    } else {
                        break outerLoop;
                    }
                }
            }
        }

        return messageBytes;
    }

    // Helper: get nth bit from byte array
    private static int getBit(byte[] data, int bitIndex) {
        int byteIndex = bitIndex / 8;
        int bitPos = 7 - (bitIndex % 8);
        return (data[byteIndex] >> bitPos) & 1;
    }

    // Helper: set nth bit in byte array
    private static void setBit(byte[] data, int bitIndex, int bitValue) {
        int byteIndex = bitIndex / 8;
        int bitPos = 7 - (bitIndex % 8);
        if (bitValue == 1) {
            data[byteIndex] |= (1 << bitPos);
        } else {
            data[byteIndex] &= ~(1 << bitPos);
        }
    }

    // Convert int to 4-byte array
    private static byte[] intToBytes(int value) {
        return new byte[] {
                (byte)(value >> 24),
                (byte)(value >> 16),
                (byte)(value >> 8),
                (byte)value
        };
    }

    // Convert 4-byte array to int
    private static int bytesToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8)  |
                (bytes[3] & 0xFF);
    }
}

