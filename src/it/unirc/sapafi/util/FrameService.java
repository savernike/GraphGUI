package it.unirc.sapafi.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class FrameService {
	private static List<JInternalFrame> listFrames = new LinkedList<JInternalFrame>();
	private static Map<JInternalFrame, JSplitPane> splitPaneParent = new HashMap<JInternalFrame, JSplitPane>();
	
	public List<JInternalFrame> getListFrames() {
		return listFrames;
	}
	public Map<JInternalFrame, JSplitPane> getSplitPaneParent() {
		return splitPaneParent;
	}
	
	private int getFramePosInList(String title) {
		int res = -1;
		for(int i = 0; i<listFrames.size(); i++) {
			if(title.equals(listFrames.get(i).getTitle())) {
				res = i;
				break;
			}
		}
		return res;
	}
	
	public void changeVisibility(String title) {
		int index = getFramePosInList(title);
		if(index == -1) {
			System.out.println("No JInternalFrame found");
			return;
		}
		JInternalFrame frame = listFrames.get(index);
		if(title.equals("Console")) {
			hideOrShowConsole(frame);
			return;
		}
	}
	
	private void hideOrShowConsole(JInternalFrame frame) {
		if(frame.isVisible()) {
			// TODO
		}
	}
	
	
}
