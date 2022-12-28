package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPath;
import com.traffic_simulator.simulation.graph.graph_elements.Node;

import java.util.HashMap;

public class PathRetriever {
    public static CarPath retrievePath(NodeNe start, NodeNe end, HashMap<NodeNe, NodeNe> hm) {
        CarPath carPath = new CarPath(start, end);
        NodeNe currentNode = end;
        while (hm.get(currentNode) != start) {
            if (currentNode == start) {
                break;
            }
            carPath.getNodes().addFirst(currentNode);
            if (currentNode.getRoadToPrev() == null) {
                System.out.println("bbbbbb");
            }
            carPath.getRoads().addFirst(currentNode.getRoadToPrev());
            currentNode = hm.get(currentNode);
        }
        carPath.getNodes().addFirst(start);

        /*for (NodeNe node : carPath.getNodes()) {
            System.out.print(node.hashCode() + " => ");
        }
        System.out.println();*/
        return carPath;
    }
}
