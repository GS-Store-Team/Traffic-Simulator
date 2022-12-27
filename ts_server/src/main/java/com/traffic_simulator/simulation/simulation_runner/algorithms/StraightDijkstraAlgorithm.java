package com.traffic_simulator.simulation.simulation_runner.algorithms;

import com.traffic_simulator.simulation.graph.graph_elements.NodeNe;
import com.traffic_simulator.simulation.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.simulation.models.road.Road;
import com.traffic_simulator.simulation.simulation_runner.algorithms.car_path.CarPathsBunch;
import com.traffic_simulator.simulation.graph.GraphMap;
import com.traffic_simulator.simulation.graph.graph_elements.Edge;
import com.traffic_simulator.simulation.graph.graph_elements.ElementColor;
import com.traffic_simulator.simulation.graph.graph_elements.Node;
import com.traffic_simulator.exceptions.PathsConstructionException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.addAll;

//TODO Придумать в каком виде извлекать путь
public class StraightDijkstraAlgorithm extends PathFindingAlgorithm {

    /**
     * Common  <a href="https://e-maxx.ru/algo/dijkstra">Dijkstra algorithm</a>.<br>
     * Called "straight" because builds routes without loops.
     */
    public StraightDijkstraAlgorithm(GraphMap graphMap) {
        super(graphMap);
    }

    @Override
    protected CarPathsBunch computeCarPath(NodeNe start) throws PathsConstructionException {
        CarPathsBunch carPathsBunch = new CarPathsBunch(start);
        List<NodeNe> unmarkedNodes = graph.getNodes();

        start.setWeight(0);
        NodeNe currentNode = start;

        HashMap<NodeNe, NodeNe> paths = new HashMap<>();

        while (!unmarkedNodes.isEmpty()) {
            unmarkedNodes.remove(currentNode);

            AttachmentPoint ref = currentNode.getAttachmentPoint();             //retrieve all roads which have a way to another nodeNe
            List<Road> refRoads = new ArrayList<>();
            refRoads.addAll(ref.getStartingRoads().stream().filter((Road r) -> !r.getRightLanes().isEmpty()).toList());
            refRoads.addAll(ref.getFinishingRoads().stream().filter((Road r) -> !r.getLeftLanes().isEmpty()).toList());

            for (NodeNe nodeNe : currentNode.getNodesList()) {
                Road road;
                try {                               //retrieve road, which is connected with current node
                     road = refRoads.stream()
                            .filter((Road r) -> ref.getStartingRoads().contains(r) & nodeNe.getAttachmentPoint().getFinishingRoads().contains(r) ||
                                    nodeNe.getAttachmentPoint().getStartingRoads().contains(r) & ref.getFinishingRoads().contains(r)).toList().get(0);
                } catch (IndexOutOfBoundsException exc) {
                    continue;
                }
                if (nodeNe.getWeight() < currentNode.getWeight() + road.getWeight()) {      //relaxation
                    nodeNe.setWeight(currentNode.getWeight() + road.getWeight());
                    if (paths.containsKey(nodeNe)) {                                //paths building
                        paths.remove(nodeNe);
                        paths.put(nodeNe, currentNode);
                    } else {
                        paths.put(nodeNe, currentNode);
                    }
                }
            }

            if (currentNode.getWeight() == Double.POSITIVE_INFINITY) {              //if there are only unreachable nodes left
                throw new PathsConstructionException("One or more nodes are unreachable!", unmarkedNodes);
                //TODO Написать исключение для недостижимой вершины в графе -> невалидный граф -> невалидная карта -> корректная симуляция невозможна
            }
        }

        for (NodeNe end : graph.getNodes()) {           //retrieve concrete paths from starting node to node
            carPathsBunch.getCarPathsEndsMap().put(end, PathRetriever.retrievePath(start, end, paths));
        }
        return carPathsBunch;
    }

}
