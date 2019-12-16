import java.io.File;
import java.util.*;

public class Solution_6 {

    private static File file = new File("./src/main/resources/input_6.txt");
    private static Map<String, OrbitNode> allOrbits = new HashMap<>();
    private static int totalCount = 0;
    private final static String ANSWER_FORMAT = "Part %s answer: %s";

    public static void main(String... args) {
        Solution_6 solution = new Solution_6();

        try (Scanner scanner = new Scanner(file)){
            solution.processOrbits(scanner.useDelimiter("\\Z").next());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(String.format(ANSWER_FORMAT, "one", totalCount));
        System.out.println(String.format(ANSWER_FORMAT,"two", getDistanceYouSan()));
    }

    void processOrbits(String data) {
        Scanner scanner = new Scanner(data);

        while(scanner.hasNextLine()) {
            String dataLine = scanner.nextLine();
            String[] orbits = dataLine.split("\\)");
            String orbitName = orbits[0];
            String orbitterName = orbits[1];

            if(allOrbits.containsKey(orbitName)) {
                if(allOrbits.containsKey(orbitterName)) {
                    allOrbits.get(orbitName).addOrbit(allOrbits.get(orbitterName));
                    allOrbits.get(orbitterName).parentOrbitNode = allOrbits.get(orbitName);
                } else {
                    OrbitNode newOrbitterNode = new OrbitNode(orbitterName, allOrbits.get(orbitName));
                    allOrbits.put(newOrbitterNode.name, newOrbitterNode);
                    allOrbits.get(orbitName).addOrbit(newOrbitterNode);
                }
            } else {
                OrbitNode newOrbitNode = new OrbitNode(orbitName, null);
                allOrbits.put(orbitName, newOrbitNode);

                if(allOrbits.containsKey(orbitterName)) {
                    newOrbitNode.addOrbit(allOrbits.get(orbitterName));
                    allOrbits.get(orbitterName).parentOrbitNode = newOrbitNode;
                } else {
                    OrbitNode newOrbitterNode = new OrbitNode(orbitterName, newOrbitNode);
                    newOrbitNode.addOrbit(newOrbitterNode);
                    allOrbits.put(orbitterName, newOrbitterNode);
                }
            }
        }

        setOrbitCount(allOrbits.get("COM"));
    }

    private static int getDistanceYouSan() {
        OrbitNode fromYouOrbit = allOrbits.get("YOU");
        OrbitNode fromSanOrbit = allOrbits.get("SAN");
        OrbitNode currFromSan = fromSanOrbit;
        OrbitNode currFromYou;
        boolean found = false;

        while(true) {
            currFromSan = currFromSan.parentOrbitNode;
            currFromYou = fromYouOrbit;

            while(true) {
                currFromYou = currFromYou.parentOrbitNode;

                if(currFromYou.name.equals("COM")) break;
                if(currFromYou.equals(currFromSan)) {
                    found = true;
                    break;
                }
            }

            if(found) {
                return fromYouOrbit.parentOrbitNode.orbitCount +
                        fromSanOrbit.parentOrbitNode.orbitCount -
                        2 * currFromSan.orbitCount;
            }
        }
    }

    private static void setOrbitCount(OrbitNode orbitNode) {
        for(OrbitNode orbitChildNode : orbitNode.orbits) {
            orbitChildNode.orbitCount = orbitNode.orbitCount + 1;
            totalCount += orbitChildNode.orbitCount;
            setOrbitCount(orbitChildNode);
        }
    }
}

class OrbitNode {
    String name;
    int orbitCount = 0;
    ArrayList<OrbitNode> orbits;
    OrbitNode parentOrbitNode;

    OrbitNode(String name, OrbitNode parentOrbitNode) {
        this.name = name;
        orbits = new ArrayList<>();
        this.parentOrbitNode = parentOrbitNode;
    }

    void addOrbit(OrbitNode orbitNode) {
        orbits.add(orbitNode);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        OrbitNode otherOrbitNode = (OrbitNode) obj;
        return otherOrbitNode.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}