import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;

public class Test {
  public static void main(String[] args) {
    Frame frame = new Frame("GetBounds2D_GlyphMetrics");
    frame.setSize(400, 400);
    frame.add(new CanvasToDisplay());
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}

class CanvasToDisplay extends Component {
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    Font font = new Font("Verdana", Font.BOLD, 16);
    FontRenderContext fontRenderContext = g2D.getFontRenderContext();
    GlyphVector glyphVector = font.createGlyphVector(fontRenderContext, "JavaTips.net");
    GlyphMetrics metrics = glyphVector.getGlyphMetrics(10);
    //Glyphs are either STANDARD, LIGATURE, COMBINING, or COMPONENT.
    boolean isStandard = metrics.isStandard();
    float glyphAdvance = metrics.getAdvance();
    System.out.println(isStandard);
    g2D.drawGlyphVector(glyphVector, 40, 60);
  }
}