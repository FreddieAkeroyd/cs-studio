/*
 * Copyright (c) 2008 Stiftung Deutsches Elektronen-Synchrotron, Member of the Helmholtz
 * Association, (DESY), HAMBURG, GERMANY. THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN
 * "../AS IS" BASIS. WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE IN
 * ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR CORRECTION. THIS
 * DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE. NO USE OF ANY SOFTWARE IS
 * AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER. DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE,
 * SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS. THE FULL LICENSE SPECIFYING FOR THE SOFTWARE
 * THE REDISTRIBUTION, MODIFICATION, USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE
 * DISTRIBUTION OF THIS PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY
 * FIND A COPY AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */

package org.csstudio.alarm.table.preferences;

import org.csstudio.platform.logging.CentralLogger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Preference Table to set the names and widths of columns in
 * message tables.
 *
 * @author jhatje
 *
 */
public class ExchangeablePreferenceColumnTableEditor extends PreferenceColumnTableEditor {



	/**
	 * Creates a new list field editor
	 */
	protected ExchangeablePreferenceColumnTableEditor() {
	}

	/**
	 * Creates a list field editor.
	 *
	 * @param name
	 *            the name of the preference this field editor works on
	 * @param labelText
	 *            the label text of the field editor
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public ExchangeablePreferenceColumnTableEditor(final String name, final String labelText,
			final Composite parent) {
		super(name, labelText, parent);
		_row = -1;
	}


	/**
	 * Set the file path and menu name set by the user from preferences in the
	 * table rows.
	 */
	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		// if (tableViewer != null) {
		String s = getPreferenceStore().getString(getPreferenceName());
		_columnTableSettings = parseString(s);
	}


	/**
	 * Update the view of this table when a new topic set in TopicsSetTable is
	 * selected.
	 *
	 * @param row
	 * @param topicTitle
	 */
	public void setSelectionToColumnEditor(final int row, final String topicTitle) {
		CentralLogger.getInstance().debug(this,
				"Selected row in topic table: " + row + " " + topicTitle);

		_mouseListener.cleanUp();

		if (_columnTableSettings == null) {
			CentralLogger.getInstance().error(this,
					"no related column settings!");
			return;
		}
		updateTopicTitle(topicTitle);

		Table table = tableViewer.getTable();
		// write current table content in the array of the previous selected
		// topic row.
		if (0 <= _row) {
			setTableSettingsToPreferenceString(table);
			table.removeAll();
		}
		_row = row;
		// put new content of the new selection of topic table to
		// this table.
		if ((0 > _row) || (_row >= _columnTableSettings.size())) {
			_currentColumnTableSet = _columnTableSettings.get(0);
			if (_currentColumnTableSet == null) {
				CentralLogger.getInstance().warn(this,
						"no column settings in default");
				return;
			}
		} else {
			_currentColumnTableSet = null;
			_currentColumnTableSet = _columnTableSettings.get(row);
		}
		TableItem item;
		for (int i = 0; i < _currentColumnTableSet.size(); i++) {
			item = new TableItem(tableViewer.getTable(), SWT.NONE);
			String[] tableRowFromPreferences = _currentColumnTableSet.get(i);
			item.setText(tableRowFromPreferences);
		}
		// _topicSetName.redraw();
		tableViewer.getTable().redraw();
	}


	public void updateTopicTitle(final String topicTitle) {
		_topicSetName.setText(topicTitle);
	}


}
