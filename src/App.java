import java.util.Arrays;

import javax.swing.*;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import dist.*;

public class App {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("bin/input-files/input.sudocode");
        SudoLexer lexer = new SudoLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SudoParser parser = new SudoParser(tokenStream);

        try {
            ParseTree tree = parser.prog();
            printTree(parser, tree); 
        }
        catch (Exception e) {
            System.out.println("An erro occured");
        }
    }
    public static void printTree(Parser parser, Tree tree) {
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);

        JFrame frame = new JFrame("Tree");
        frame.add(viewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
