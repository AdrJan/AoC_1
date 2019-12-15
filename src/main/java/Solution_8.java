import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution_8 {
    private final static int WIDE = 25;
    private final static int TALL = 6;
    private final static int TOTAL_PIXEL_FOR_LAYER = WIDE * TALL;
    private final static String ANSWER_FORMAT = "Part %s answer:\n%s";

    public static void main(String... args) {
        File file = new File("./src/main/resources/input_8.txt");
        ArrayList<LayerInfo> layerInfos = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String input = scanner.next();
                processLayers(input, layerInfos);
            }

            LayerInfo layerInfoWithFewestZeroes = getLayerWithFewestZeroes(layerInfos);

            System.out.println(String.format(ANSWER_FORMAT, "one", layerInfoWithFewestZeroes.ones * layerInfoWithFewestZeroes.twos));
            System.out.println(String.format(ANSWER_FORMAT, "two", getFormattedImage(layerInfos)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static LayerInfo getLayerWithFewestZeroes(ArrayList<LayerInfo> layerInfos) {
        LayerInfo layerInfoWithFewestZeroes = layerInfos.get(0);

        for (LayerInfo layerInfo : layerInfos) {
            if (layerInfo.zeroes < layerInfoWithFewestZeroes.zeroes) {
                layerInfoWithFewestZeroes = layerInfo;
            }
        }

        return layerInfoWithFewestZeroes;
    }

    private static void processLayers(String input, ArrayList<LayerInfo> layerInfos) {
        LayerInfo layerInfo = null;

        for (int i = 0; i < input.length(); i++) {
            if (i % TOTAL_PIXEL_FOR_LAYER == 0) {
                layerInfo = new LayerInfo();
                layerInfo.content = input.substring(i, i + TOTAL_PIXEL_FOR_LAYER);
                layerInfos.add(layerInfo);
            }

            switch (input.charAt(i)) {
                case '0':
                    layerInfo.incZeroes();
                    break;
                case '1':
                    layerInfo.incOnes();
                    break;
                case '2':
                    layerInfo.incTwos();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private static String getFormattedImage(ArrayList<LayerInfo> layerInfos) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < TOTAL_PIXEL_FOR_LAYER; i++) {
            for (LayerInfo layerInfo : layerInfos) {
                if (layerInfo.content.charAt(i) == '0') {
                    result.append(' ');
                    break;
                }
                if (layerInfo.content.charAt(i) == '1') {
                    result.append('*');
                    break;
                }
            }
            if ((i + 1) % WIDE == 0) {
                result.append('\n');
            }
        }

        return result.toString();
    }
}

class LayerInfo {
    String content;
    int zeroes;
    int ones;
    int twos;

    LayerInfo() {
        this.zeroes = 0;
        this.ones = 0;
        this.twos = 0;
    }

    void incZeroes() {
        zeroes++;
    }

    void incOnes() {
        ones++;
    }

    void incTwos() {
        twos++;
    }
}
