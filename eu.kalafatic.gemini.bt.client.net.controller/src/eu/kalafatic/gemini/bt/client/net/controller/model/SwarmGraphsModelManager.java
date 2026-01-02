///**
// * 
// */
//package eu.kalafatic.gemini.bt.client.net.controller.model;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//
///**
// * @author Petr Kalafatic
// * @project Gemini 2.0
// */
//public class SwarmGraphsModelManager {
//
//	// private DrawComposite drawComposite;
//	// private TreeModel model;
//	// private SwarmGraphsEditor activeEditor;
//	// private DijkstraAnalyzer dijkstraAnalyzer;
//	//
//	// The shared instance
//	private static SwarmGraphsModelManager INSTANCE;
//
//	public SwarmGraphsModelManager() {
//		init();
//	}
//
//	public static SwarmGraphsModelManager getInstance() {
//		if (INSTANCE == null) {
//			synchronized (SwarmGraphsModelManager.class) {
//				INSTANCE = new SwarmGraphsModelManager();
//			}
//		}
//		return INSTANCE;
//	}
//
//	// ---------------------------------------------------------------
//	// ---------------------------------------------------------------
//
//	private void init() {
//		
//	}
//
//	// ---------------------------------------------------------------
//
//	public Map<String, GraphNode>  getModel() {		
//		
//		Map<String,GraphNode>returnMap=new HashMap<String, GraphNode>();
//		
//		SwarmTreeRoot swarmTreeRoot = SwarmTreeModelManager.getInstance().getSwarmTreeRoot();
//		Iterator<SwarmTreeObject> iterator = swarmTreeRoot.getEntry().map().values().iterator();
//		
//		while (iterator.hasNext()) {
//			SwarmTreeObject swarmTreeObject = (SwarmTreeObject) iterator.next();
//			GraphNode node;
//			if (swarmTreeObject.getNode()==null) {
//				node=createNode(swarmTreeObject);
//			}else{
//				node=swarmTreeObject.getNode();
//			}
//			returnMap.put(swarmTreeObject.getAnnounce(), node);
//			
//		}			
//		return returnMap;
//	}
//
//	private GraphNode createNode(SwarmTreeObject swarmTreeObject) {
//		GraphNode node = SwarmTreeFactory.eINSTANCE.createGraphNode();				
//		return node;
//		
//	}
//	
//	
//	// ---------------------------------------------------------------
//
//	
//
// }
