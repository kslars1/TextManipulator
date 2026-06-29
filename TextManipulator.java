import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;





public class TextManipulator {

    //declare components as fields so they can be accessed throughout the class
    public JFrame frame;
    public JTextArea txtEditor;
    public JButton btnCsvToHtml;
    public JButton btnRemoveHtml;





    //---------------------------------------------------------------------------------------------------------------------
    // main entry point, constructors and component initialization
    //---------------------------------------------------------------------------------------------------------------------
    
    //
    // main
    //
    public static void main(String[] args) {
        //init this class
        TextManipulator tm = new TextManipulator();
        tm.frame.setVisible(true);
    }


    //
    // constructor
    //
    public TextManipulator() {
        initializeComponents();
    }



    //
    // Initializes the GUI components, this is where we will set up 
    // initial buttons and other UI elements.
    // 
    public void initializeComponents() {

        //some defaults
        int appTitleBarHeight = 30;                             // approximate height of the title bar
        int frameMargin = 20;

        int buttonWidth = 120;
        int buttonHeight = 22;

        
        //FRAME: set up basic simple frame as the container
        frame = new JFrame("Text Manipulator");
        frame.setLayout(null);                          //this forces absolute positioning for all components
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TEXTAREA: main IO for manipulation
        txtEditor = new JTextArea();
        txtEditor.setEditable(true);
        txtEditor.setSize(frame.getWidth() - 3 * frameMargin - buttonWidth, frame.getHeight() - 2 * frameMargin - appTitleBarHeight);
        txtEditor.setLocation(frameMargin, frameMargin);
        frame.add(txtEditor);

        //BUTTON: CSV to HTML table
        btnCsvToHtml = new JButton("Remove HTML");
        btnCsvToHtml.addActionListener(e -> btnRemoveHtmlClicked());
        btnCsvToHtml.setSize(buttonWidth, buttonHeight);
        btnCsvToHtml.setLocation(frame.getWidth() - buttonWidth - frameMargin, frameMargin);
        frame.add(btnCsvToHtml);

        //BUTTON: remove HTML
        btnRemoveHtml = new JButton("CSV to HTML");
        btnRemoveHtml.addActionListener(e -> btnCsvToHtmlClicked());
        btnRemoveHtml.setSize(buttonWidth, buttonHeight);
        btnRemoveHtml.setLocation(frame.getWidth() - buttonWidth - frameMargin, frameMargin + 1 * (buttonHeight + 4));
        frame.add(btnRemoveHtml);

        //BUTTON: remove HTML
        btnRemoveHtml = new JButton("Tab to HTML");
        btnRemoveHtml.addActionListener(e -> btnTabToHtmlClicked());
        btnRemoveHtml.setSize(buttonWidth, buttonHeight);
        btnRemoveHtml.setLocation(frame.getWidth() - buttonWidth - frameMargin, frameMargin + 2 * (buttonHeight + 4));
        frame.add(btnRemoveHtml);

        //finalize frame setup
        frame.setVisible(true);
    }





    //---------------------------------------------------------------------------------------------------------------------
    // general functions
    //---------------------------------------------------------------------------------------------------------------------

    //
    // removes double spaces after removals
    //
    private String normalizeSpacing(String txt)
    {
        String ret = removeHtml(txtEditor.getText());
        ret = ret.replaceAll("  ", " ");  
        return ret;
    }
    
    
    
    //
    // Converts a CSV formatted string into an HTML table representation. Each row in the CSV becomes a table row, and each comma-separated value becomes a table cell.
    // 
    private String csvToHtml(String txt)
    {
        StringBuilder html = new StringBuilder();
        html.append("<table>\n");
        String[] rows = txt.split("\n");
        for (String row : rows) {
            html.append("  <tr>\n");
            String[] cols = row.split(",");
            for (String col : cols) {
                html.append("    <td>").append(col).append("</td>\n");
            }
            html.append("  </tr>\n");
        }
        html.append("</table>");
        return html.toString();
    }



    //
    // removes double spaces after removals
    //
    private String tabToHtml(String txt)
    {
        String ret = removeHtml(txtEditor.getText());
        ret = ret.replaceAll("\t", ",");
        ret = csvToHtml(ret);
        return ret;
    }
    
    
    
    //
    // Removes HTML tags from the given string
    //
    private String removeHtml(String txt)
    {
        return txt.replaceAll("<[^>]*>", "");
    }



    //
    //
    //





    //---------------------------------------------------------------------------------------------------------------------
    // window and component form events
    //---------------------------------------------------------------------------------------------------------------------
    
    private void btnRemoveHtmlClicked() {
        String ret = removeHtml(txtEditor.getText());
        ret = normalizeSpacing(ret);
        txtEditor.setText(ret);
    }


    
    private void btnCsvToHtmlClicked() {
        txtEditor.setText(csvToHtml(txtEditor.getText()));
    }
    


    private void btnTabToHtmlClicked() {
        txtEditor.setText(tabToHtml(txtEditor.getText()));
    }

    
}