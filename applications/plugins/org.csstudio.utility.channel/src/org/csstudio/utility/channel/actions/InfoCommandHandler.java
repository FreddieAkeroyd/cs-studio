/**
 * 
 */
package org.csstudio.utility.channel.actions;

import gov.bnl.channelfinder.api.Channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.csstudio.utility.channel.ChannelCommandHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 */
public class InfoCommandHandler extends ChannelCommandHandler {

	@Override
	protected void execute(List<Channel> channels, ExecutionEvent event) {
		try {
			Shell shell = HandlerUtil.getActiveShell(event);
			DisplayTreeDialog displayTreeDialog = new DisplayTreeDialog(shell,
					new ChannelTreeLabelProvider(),
					new ChannelTreeContentProvider());
			displayTreeDialog.setInput(createChannelModel(channels));
			displayTreeDialog.setBlockOnOpen(true);
			displayTreeDialog.setMessage(Messages.treeDialogMessage);
			displayTreeDialog.setTitle(Messages.treeDialogTitle);
			displayTreeDialog.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object createChannelModel(Collection<Channel> channels){
		ChannelTreeModel root = new ChannelTreeModel(0,null);		
		for (Channel channel : channels) {
			root.getChild().add(channel);
		}
		return root;		
	}
}
