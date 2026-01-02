/**
 * 
 */
package eu.kalafatic.gemini.bt.client.themes.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import eu.kalafatic.gemini.bt.client.themes.constants.IThemeConstants;

/**
 * @author  Petr Kalafatic
 * @project Gemini 2.0
 */
public class ThemeSelect extends Dialog implements IThemeConstants {

	int choose;

	public ThemeSelect(Shell parent, int choose) {
		super(parent);
		this.choose = choose;
	}

	/**
	 * 
	 * @return
	 */
	public void open() {

		Shell parent = getParent();
		final Shell dialog = new Shell(parent, SWT.NO_TRIM
				| SWT.APPLICATION_MODAL);
		
		dialog.setSize(120, 120);
		
		
//		Region region = new Region();
//	    Rectangle pixel = new Rectangle(0, 0, 1, 1);
//	    for (int y = 0; y < 100; y += 2) {
//	      for (int x = 0; x < 100; x += 2) {
//	        pixel.x = x;
//	        pixel.y = y;
//	        region.add(pixel);
//	      }
//	    }	    
//	    final Image image = Display.getDefault().getSystemImage(SWT.ICON_WARNING);
////		
//	    dialog.setRegion(region);
//	    Rectangle size = region.getBounds();
//	    dialog.setSize(size.width, size.height);
//	    dialog.addPaintListener(new PaintListener() {
//	      public void paintControl(PaintEvent e) {
//	        Rectangle bounds = image.getBounds();
//	        Point size = dialog.getSize();
//	        e.gc.drawImage(image, 0, 0, bounds.width, bounds.height, 10,
//	            10, size.x - 20, size.y - 20);
//	      }
//	    });
	    
	    
		dialog.setLocation(Display.getCurrent().getCursorLocation());
		dialog.setText("Block size");
		dialog.setLayout(new GridLayout(2, true));
		dialog.setLayoutData(new GridData(GridData.FILL_BOTH));

		for (int i = 0; i < THEMES.length; i++) {

			final Button btn = new Button(dialog, SWT.PUSH);
			btn.setBackground(new Color(dialog.getDisplay(), 0, 150, 150));
			GridData gridData = new GridData();
			gridData.widthHint = 50;
			gridData.heightHint = 18;
			btn.setLayoutData(gridData);

			btn.setText(THEMES[i]);
			
			btn.setData(Integer.toString(i));
			
			btn.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					choose=Integer.valueOf((String) btn.getData());
					dialog.close();
					dialog.dispose();

				}
			});
		}
		dialog.open();
		dialog.setFocus();

		Display display = parent.getDisplay();
		while (!parent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
