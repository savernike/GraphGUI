package it.unirc.sapafi.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class FrameService {
	private static List<JInternalFrame> listFrames = new LinkedList<JInternalFrame>();
	
	public List<JInternalFrame> getListFrames() {
		return listFrames;
	}
	
	public int getFramePosInList(String title) {
		int res = -1;
		for(int i = 0; i<listFrames.size(); i++) {
			if(title.equals(listFrames.get(i).getTitle())) {
				res = i;
				break;
			}
		}
		return res;
	}
	
}
