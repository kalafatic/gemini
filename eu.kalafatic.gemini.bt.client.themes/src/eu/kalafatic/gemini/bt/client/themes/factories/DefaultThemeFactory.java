/**
 * 
 */
package eu.kalafatic.gemini.bt.client.themes.factories;

import java.util.Hashtable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.themes.IColorFactory;

/**
 * @author kalafpet
 *
 */
public class DefaultThemeFactory implements IColorFactory ,IExecutableExtension {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.themes.IColorFactory#createColor()
	 */
	private String color1;
	private String color2;

	@Override
	public RGB createColor() {
//		return  new RGB(255,255,0);
		if (color1 == null && color2 == null) {
			             return new RGB(0, 0, 0);
			         } else if (color1 != null && color2 == null) {
			             return ColorUtils.getColorValue(color1);
			         } else if (color1 == null && color2 != null) {
			             return ColorUtils.getColorValue(color2);
			         } else {
			             RGB rgb1 = ColorUtils.getColorValue(color1);
			             RGB rgb2 = ColorUtils.getColorValue(color2);
			             return ColorUtils.blend(rgb1, rgb2);
			         }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {

		if (data instanceof Hashtable) {
			             Hashtable table = (Hashtable) data;
			             color1 = (String) table.get("color1"); //$NON-NLS-1$
			             color2 = (String) table.get("color2"); //$NON-NLS-1$            
			         }
		
	}
	

}
