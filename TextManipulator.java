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
    // main entry point and component initialization
    //---------------------------------------------------------------------------------------------------------------------
    
    //
    // main
    //
    public static void main(String[] args) {
        //init this class
        TextManipulator tm = new TextManipulator();

        //initialize GUI components
        tm.initializeComponents();
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
        btnCsvToHtml = new JButton("CSV to HTML");
        btnCsvToHtml.addActionListener(e -> btnCsvToHtmlClicked());
        btnCsvToHtml.setSize(buttonWidth, buttonHeight);
        btnCsvToHtml.setLocation(frame.getWidth() - buttonWidth - frameMargin, frameMargin);
        frame.add(btnCsvToHtml);

        //BUTTON: remove HTML
        btnRemoveHtml = new JButton("Remove HTML");
        btnRemoveHtml.addActionListener(e -> btnRemoveHtmlClicked());
        btnRemoveHtml.setSize(buttonWidth, buttonHeight);
        btnRemoveHtml.setLocation(frame.getWidth() - buttonWidth - frameMargin, frameMargin + 1 * (buttonHeight + 4));
        frame.add(btnRemoveHtml);

        //finalize frame setup
        frame.setVisible(true);
    }





    //---------------------------------------------------------------------------------------------------------------------
    // general functions
    //---------------------------------------------------------------------------------------------------------------------

    //
    // Converts a CSV formatted string into an HTML table representation. Each row in the CSV becomes a table row, and each comma-separated value becomes a table cell.
    // 
    private String doCsvToHtml(String txt)
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
    // Removes HTML tags from the given string, leaving only the plain text content.
    //
    private String doRemoveHtml(String txt)
    {
        return txt.replaceAll("<[^>]*>", "");
    }





    //---------------------------------------------------------------------------------------------------------------------
    // windows form events
    //---------------------------------------------------------------------------------------------------------------------
    
    private void btnCsvToHtmlClicked() {
        txtEditor.setText(doCsvToHtml(txtEditor.getText()));
    }
    
    private void btnRemoveHtmlClicked() {
        String ret = doRemoveHtml(txtEditor.getText());
        ret = ret.replaceAll("  ", " ");        //clean up double spaces resulting from removed HTML tags
        txtEditor.setText(ret);
    }
}