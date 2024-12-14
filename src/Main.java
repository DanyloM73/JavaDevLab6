import electrical_appliances.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the functionality of the {@link ElectricalApplianceSet} class
 * with various {@link electrical_appliances.ElectricalAppliance} subclasses.
 */
public class Main {
    /**
     * The entry point of the application. This method tests the various constructors
     * and methods of the {@link ElectricalApplianceSet} class.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create instances of various electrical appliances
        ElectricStove stove = new ElectricStove("Bosch");
        Hairdryer hairdryer = new Hairdryer("Samsung");
        Microwave microwave = new Microwave("LG");
        VacuumCleaner vacuumCleaner = new VacuumCleaner("Philips");
        WashingMachine washingMachine = new WashingMachine("Panasonic");

        System.out.println("\n=== ElectricalApplianceSet constructors ===");

        // Test empty constructor
        ElectricalApplianceSet<ElectricStove> emptySet = new ElectricalApplianceSet<>();
        System.out.println("Empty set: " + emptySet);

        // Test single element constructor
        ElectricalApplianceSet<ElectricalAppliance> singleSet = new ElectricalApplianceSet<>(stove);
        System.out.println("Single set: " + singleSet);

        // Test collection constructor
        List<ElectricalAppliance> appliancesList = new ArrayList<>();
        appliancesList.add(hairdryer);
        appliancesList.add(washingMachine);
        ElectricalApplianceSet<ElectricalAppliance> collectionSet = new ElectricalApplianceSet<>(appliancesList);
        System.out.println("Collection set: " + collectionSet);

        System.out.println("\n=== ElectricalApplianceSet methods ===");

        // Test adding a new element
        System.out.println("\nAdding vacuum cleaner to collection set...");
        collectionSet.add(vacuumCleaner);
        System.out.println("Collection set after adding new element: " + collectionSet);

        // Test adding a duplicate element
        System.out.println("\nAdding same washing machine to collection set...");
        collectionSet.add(washingMachine);
        System.out.println("Collection set after adding new element: " + collectionSet);

        // Test adding a list of elements
        System.out.println("\nAdding list of electrical appliances to collection set...");
        appliancesList.add(microwave);
        appliancesList.add(stove);
        collectionSet.addAll(appliancesList);
        System.out.println("List of electrical appliances:\n" + appliancesList);
        System.out.println("Collection set after adding list of new elements: " + collectionSet);

        // Test contains method
        System.out.println("\nDoes collection set contain hairdryer? " + collectionSet.contains(hairdryer));

        // Test containsAll method
        System.out.println("\nList of electrical appliances:\n" + appliancesList);
        System.out.println("Does collection set contain all electrical appliances from list? " + collectionSet.containsAll(appliancesList));

        // Test remove method
        System.out.println("\nRemoving hairdryer from collection set...");
        collectionSet.remove(hairdryer);
        System.out.println("Collection set after removing element: " + collectionSet);

        // Test size method
        System.out.println("\nSize of collection set: " + collectionSet.size());

        // Test toArray methods
        System.out.println("\nConverting collection set to Arrays...");
        ElectricalAppliance[] applianceArray1 = collectionSet.toArray(new ElectricalAppliance[0]);
        System.out.println("\nFirst array elements: ");
        for (ElectricalAppliance appliance : applianceArray1) {
            System.out.println(appliance);
        }
        ElectricalAppliance[] applianceArray2 = (ElectricalAppliance[]) collectionSet.toArray();
        System.out.println("\nSecond array elements: ");
        for (ElectricalAppliance appliance : applianceArray2) {
            System.out.println(appliance);
        }
        // Iterate over the collection set
        System.out.println("\nCollection set elements: ");
        for (ElectricalAppliance appliance : collectionSet) {
            System.out.println(appliance);
        }

        // Test removeAll method
        System.out.println("\nRemoving some elements from collection set...");
        appliancesList.remove(stove);
        collectionSet.removeAll(appliancesList);
        System.out.println("\nList of electrical appliances to remove:\n" + appliancesList);
        System.out.println("Collection set after removing elements: " + collectionSet);
        // Test isEmpty method
        System.out.println("\nIs collection set empty? " + collectionSet.isEmpty());

        // Test retainAll method
        System.out.println("\nRetaining some elements from collection set...");
        collectionSet.retainAll(appliancesList);
        System.out.println("\nList of electrical appliances using to retain:\n" + appliancesList);
        System.out.println("Collection set after retaining elements: " + collectionSet);
        // Test isEmpty method
        System.out.println("\nIs collection set empty? " + collectionSet.isEmpty());

        // Test clear method
        System.out.println("\nClearing single set...");
        singleSet.clear();
        System.out.println("Single set after clearing: " + collectionSet);
    }
}
