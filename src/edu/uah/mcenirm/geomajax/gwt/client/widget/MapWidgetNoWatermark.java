// Copyright 2011 The University of Alabama in Huntsville
// Since Geomajas uses the GNU Affero General Public License,
// so does this file. <http://www.gnu.org/licenses/agpl.html>

package edu.uah.mcenirm.geomajax.gwt.client.widget;

import java.util.Map;

import org.geomajas.command.dto.GetMapConfigurationResponse;
import org.geomajas.gwt.client.gfx.paintable.mapaddon.MapAddon;
import org.geomajas.gwt.client.gfx.paintable.mapaddon.Watermark;
import org.geomajas.gwt.client.widget.MapWidget;

/**
 * A MapWidget that removes the hard-coded watermark "powered by geomajas".
 * Tacky and ungrateful to the nice Geomajas folks, but necessary sometimes.
 * 
 * @author mcenirm
 */

public class MapWidgetNoWatermark extends MapWidget {

	public MapWidgetNoWatermark(String id, String applicationId) {
		super(id, applicationId);
	}

	@Override
	protected void initializationCallback(GetMapConfigurationResponse r) {
		// Let superclass register Watermark map addon with id + "-watermark"
		super.initializationCallback(r);
		// And then unregister it
		String watermarkId = getID() + "-watermark";
		Map<String, MapAddon> mapAddons = getMapAddons();
		for (Map.Entry<String, MapAddon> entry : mapAddons.entrySet()) {
			String addonId = entry.getKey();
			MapAddon addon = entry.getValue();
			if (watermarkId.equals(addonId) && (addon instanceof Watermark)) {
				unregisterMapAddon(addon);
			}
		}
	}
}
