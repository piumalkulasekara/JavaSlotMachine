
package SlotMachine;

import java.util.ArrayList;
import javax.swing.ImageIcon;

 
public interface ISymbol {
     //create all public abstract methods
    public void setImage(String imgPath);

    public String getImage();

    public void setValue(int v);

    public int getValue();
}
