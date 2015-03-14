package se.kth.csc.iprog.agendabuilder.controller;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;

import se.kth.csc.iprog.agendabuilder.model.Activity;
import se.kth.csc.iprog.agendabuilder.model.AgendaModel;
import se.kth.csc.iprog.agendabuilder.swing.view.DayPanel;
import se.kth.csc.iprog.agendabuilder.swing.view.ActivityPanel.TransferableActivity;

public class MyDropTargetListener extends DropTargetAdapter {
	
	AgendaModel m;
	DayPanel dp;

	private DropTarget dropTarget;

	public MyDropTargetListener() {
	}
	
	public void startDropListen()
	{
		dropTarget = new DropTarget(dp.getPanel(), DnDConstants.ACTION_COPY, 
				this, true, null);
	}
	
	public void addModel(AgendaModel model)
	{
		this.m = model;
	}
	
	public void addView(DayPanel v){
		this.dp = v;
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		try {

			Transferable tr = event.getTransferable();
			Activity activity = (Activity) tr.getTransferData(TransferableActivity.activityFlavor);

			if (event.isDataFlavorSupported(TransferableActivity.activityFlavor)) {

				event.acceptDrop(DnDConstants.ACTION_COPY);
				m.addActivity(activity, dp.getDay(), dp.getDay().activities.size());
				event.dropComplete(true);
				
				return;
			}
			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}


	


}