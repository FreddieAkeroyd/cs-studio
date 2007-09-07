/* 
 * Copyright (c) 2006 Stiftung Deutsches Elektronen-Synchroton, 
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS. 
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND 
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR 
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE 
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR 
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE. 
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, 
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION, 
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS 
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY 
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */
package org.csstudio.sds.components.model;

import java.util.HashMap;
import java.util.Map;

import org.csstudio.sds.components.internal.localization.Messages;
import org.csstudio.sds.model.AbstractWidgetModel;
import org.csstudio.sds.model.WidgetPropertyCategory;
import org.csstudio.sds.model.properties.ActionData;
import org.csstudio.sds.model.properties.ActionDataProperty;
import org.csstudio.sds.model.properties.ActionTypes;
import org.csstudio.sds.model.properties.DoubleProperty;
import org.csstudio.sds.model.properties.FontProperty;
import org.csstudio.sds.model.properties.OptionProperty;
import org.csstudio.sds.model.properties.ResourceProperty;
import org.csstudio.sds.model.properties.StringMapProperty;
import org.csstudio.sds.model.properties.StringProperty;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

/**
 * An action button widget model.
 * 
 * @author Sven Wende
 * @version $Revision$
 * 
 */
public final class ActionButtonModel extends AbstractWidgetModel {
	/**
	 * The ID of the label property.
	 */
	public static final String PROP_LABEL = "label"; //$NON-NLS-1$

	/**
	 * The ID of the resource property.
	 */
	public static final String PROP_RESOURCE = "resource"; //$NON-NLS-1$
	
	/**
	 * The ID of the click value property.
	 */
	public static final String PROP_CLICK_VALUE = "click_value"; //$NON-NLS-1$
	
	/**
	 * The ID of the click alias property.
	 */
	public static final String PROP_CLICK_ALIAS = "click_alias"; //$NON-NLS-1$

	/**
	 * The ID of the action property.
	 */
	public static final String PROP_ACTION = "action"; //$NON-NLS-1$

	/**
	 * The ID of the font property.
	 */
	public static final String PROP_FONT = "font"; //$NON-NLS-1$
	
	/**
	 * The ID of the text alignment property.
	 */
	public static final String PROP_TEXT_ALIGNMENT = "textAlignment"; //$NON-NLS-1$
	
	/**
	 * The ID of the {@link ActionData} property.
	 */
	public static final String PROP_ACTIONDATA = "actionData"; //$NON-NLS-1$

	/**
	 * The ID of this widget model.
	 */
	public static final String ID = "org.csstudio.sds.components.ActionButton"; //$NON-NLS-1$

	/**
	 * The default value of the height property.
	 */
	private static final int DEFAULT_HEIGHT = 20;

	/**
	 * The default value of the width property.
	 */
	private static final int DEFAULT_WIDTH = 80;
	
	/**
	 * The default value of the text alignment property.
	 */
	private static final int DEFAULT_TEXT_ALIGNMENT = 0;
	
	/**
	 * The labels for the text alignment property.
	 */
	private static final String[] SHOW_LABELS = new String[] {"Center", "Top", "Bottom", "Left", "Right"};
	/**
	 * The default value for the file extensions.
	 */
	private static final String[] FILE_EXTENSIONS = new String[] {"css-sds"};

	/**
	 * Standard constructor.
	 */
	public ActionButtonModel() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeID() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configureProperties() {
		addProperty(PROP_LABEL, new StringProperty(Messages.LabelElement_LABEL,
				WidgetPropertyCategory.Display, "")); //$NON-NLS-1$
		addProperty(PROP_RESOURCE, new ResourceProperty("Resource",
				WidgetPropertyCategory.Behaviour, new Path(""), FILE_EXTENSIONS));
		addProperty(PROP_ACTION, new OptionProperty("Action",
				WidgetPropertyCategory.Behaviour, new String[] {
						"Open Display As Shell", "Open Display As View", "Set Value" }, 0));
		addProperty(PROP_FONT, new FontProperty("Font",
				WidgetPropertyCategory.Display, new FontData(
						"Arial", 8, SWT.NONE))); //$NON-NLS-1$
		addProperty(PROP_TEXT_ALIGNMENT, new OptionProperty("Text Alignment", 
				WidgetPropertyCategory.Display, SHOW_LABELS, DEFAULT_TEXT_ALIGNMENT));
		addProperty(PROP_CLICK_VALUE, new DoubleProperty("Click Value",
				WidgetPropertyCategory.Behaviour, 0));
		addProperty(PROP_CLICK_ALIAS, new StringMapProperty("Click Alias", 
				WidgetPropertyCategory.Behaviour, new HashMap<String, String>()));
		addProperty(PROP_ACTIONDATA, new ActionDataProperty("Action Data",
				WidgetPropertyCategory.Behaviour, new ActionData(ActionTypes.UNKNOWN)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDoubleTestProperty() {
		return PROP_LABEL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColorTestProperty() {
		return PROP_COLOR_BACKGROUND;
	}
	
	public ActionData getActionData() {
		return (ActionData) getProperty(PROP_ACTIONDATA).getPropertyValue();
	}

	/**
	 * Return the label text.
	 * 
	 * @return The label text.
	 */
	public String getLabel() {
		return (String) getProperty(PROP_LABEL).getPropertyValue();
	}

	/**
	 * Return the label font.
	 * 
	 * @return The label font.
	 */
	public FontData getFont() {
		return (FontData) getProperty(PROP_FONT).getPropertyValue();
	}
	
	/**
	 * Returns the alignment for the text.
	 * @return int 
	 * 			0 = Center, 1 = Top, 2 = Bottom, 3 = Left, 4 = Right
	 */
	public int getTextAlignment() {
		return (Integer) getProperty(PROP_TEXT_ALIGNMENT).getPropertyValue();
	}

	/**
	 * Return the target resource.
	 * 
	 * @return The target resource.
	 */
	public IPath getResource() {
		return (IPath) getProperty(PROP_RESOURCE).getPropertyValue();
	}
	
	/**
	 * Return the click value.
	 * 
	 * @return The click value.
	 */
	public double getClickValue() {
		return (Double) getProperty(PROP_CLICK_VALUE).getPropertyValue();
	}
	
	/**
	 * Return the click alias.
	 * 
	 * @return The click alias.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getClickAlias() {
		return (Map<String, String>) getProperty(PROP_CLICK_ALIAS).getPropertyValue();
	}

	/**
	 * Return the index of the action that should be performed.
	 * 
	 * @return The index of the action that should be performed.
	 */
	public int getAction() {
		return (Integer) getProperty(PROP_ACTION).getPropertyValue();
	}
}
