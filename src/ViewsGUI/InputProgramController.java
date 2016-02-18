package ViewsGUI;
import controller.Controller;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.Heap.LibHeap;
import domain.DataStructures.LatchTable.LatchTable;
import domain.DataStructures.List.LibList;
import domain.DataStructures.Stack.LibStack;
import domain.Expression.*;
import domain.MyBuffer;
import domain.PrgState;
import domain.Stmt.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import view.InputDataTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputProgramController {

    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void actionBtnBack(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }


    public void actionBtnNewPrg(ActionEvent event) throws IOException {
        //IStmt program = newStatement("New Program");

        LibStack stk = new LibStack<>();
        LibDictionary dict = new LibDictionary<>();
        LibList lst = new LibList<>();
        LibHeap heap = new LibHeap<>();
        LibDictionary fileT = new LibDictionary<>();
        LatchTable latchTable = new LatchTable<>();
//        IStmt prgStatement =new CompStmt(new OpenFile("a.txt"),
//                new CompStmt(
//                                new WriteFile("a.txt", new ConstExp(2)),
//                                new CompStmt(
//                                new WriteFile("a.txt", new ConstExp(10)),
//                                        new CompStmt(
//                                        new WriteFile("a.txt", new ConstExp(5)),
//                                                new CompStmt(
//                new WriteFile("a.txt", new ConstExp(1)), new CloseFileStmt("a.txt"))))));


//        new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,3);
//        fork(wh(v1,rh(v1)*10));
//        print(rh(v1));countDown(cnt));
//        fork(wh(v2,rh(v2)*10));
//        print(rh(v2));countDown(cnt));
//        fork(wh(v3,rh(v3)*10));
//        print(rh(v3));countDown(cnt));
//        await(cnt);
//        print(cnt);
//        countDown(cnt);
//        print(cnt)
        IStmt prgStatement = new CompStmt(new NewStmt("v1", new ConstExp(2)),
                            new CompStmt(new NewStmt("v2", new ConstExp(3)),
                            new CompStmt(new NewStmt("v3", new ConstExp(4)),
                            new CompStmt(new newLatchStmt("cnt", new ConstExp(3)),
                            new CompStmt(new ForkStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp("v1"), new ConstExp(10), "*") )),
                                    new CompStmt(new PrintStmt(new HeapReadExp("v1")),
                                            new CompStmt(new CountDownStmt("cnt"),
                            new CompStmt(new ForkStmt(new HeapWriteStmt("v2", new ArithExp(new HeapReadExp("v2"), new ConstExp(10), "*"))),
                                            new CompStmt(new PrintStmt(new HeapReadExp("v2")),
                                                   new CompStmt(new CountDownStmt("cnt"),
                            new CompStmt(new ForkStmt(new HeapWriteStmt("v3", new ArithExp(new HeapReadExp("v3"), new ConstExp(10), "*"))),
                                        new CompStmt(new PrintStmt(new HeapReadExp("v3")),
                                                 new CompStmt(new CountDownStmt("cnt"),
                            new CompStmt(new AwaitStmt("cnt"),
                                    new CompStmt(new PrintStmt(new VarExp("cnt")),
                                            new CompStmt(new CountDownStmt("cnt"), new PrintStmt(new VarExp("cnt"))))))))))))))))));


        List<PrgState> prgStates = new ArrayList<>();

        PrgState prgS = new PrgState(1, stk, dict, lst, heap, fileT,latchTable, prgStatement);
        prgStates.add(prgS);

        ctrl.getRepo().setPrgList(prgStates);

        this.preview.setText(prgStatement.toString());
    }

    public String newString(String content) throws IOException{
        TextInputDialog inputDialog = new TextInputDialog("");
        inputDialog.setContentText(content);
        Optional<String> result = inputDialog.showAndWait();
        if(result.isPresent()){return result.get();}
        throw new IOException();
    }


    public IStmt newStatement(String content) throws IOException{
        String[] items = {"Compound", "Assign", "Fork", "If", "If Then Skip", "Print", "Increment", "While", "Switch", "New", "Write Heap", "Open File",
                            "Write File", "Close File"};
        ChoiceDialog choiceStmt = new ChoiceDialog(items[0], items);

        choiceStmt.setTitle("New Statement");
        choiceStmt.setContentText(content);

        Optional<String> result = choiceStmt.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals("Compound")) {
                IStmt first = newStatement("First statement: ");
                IStmt second = newStatement("Second statement: ");
                return new CompStmt(first, second);
            }
            if (choice.equals("Assign")) {
                String id = newString("Variable name");
                Exp expression = newExpression("Value");
                return new AssignStmt(id, expression);
            }
            if (choice.equals("If")) {
                Exp expression = newExpression("Expression");
                IStmt thenS = newStatement("Then statement");
                IStmt elseS = newStatement("Else statement");
                return new IfStmt(expression, thenS, elseS);
            }
            if (choice.equals("If Then Skip")) {
                Exp expression = newExpression("Expression");
                IStmt thenS = newStatement("Then statement");
                return new IfSkipStmt(expression, thenS);
            }
            if (choice.equals("Print")) {
                Exp expression = newExpression("Expression");
                return new PrintStmt(expression);
            }
            if (choice.equals("Increment")) {
                Exp expression = newExpression("Expression");
                return new IncStmt(expression);
            }
            if (choice.equals("While")) {
                Exp exp = newExpression("While Expression");
                IStmt stmt = newStatement("Statement");
                return new WhileStmt(exp, stmt);
            }
            if (choice.equals("Switch")) {
                String varname = newString("Varname");
                Exp exp;
                String opt;
                IStmt stmt;
                IDictionary<Exp, IStmt> tbl = new LibDictionary<>();
                while (true) {
                    exp = newExpression("Expression (case)");
                    stmt = newStatement("Statement");
                    try {
                        tbl.add(exp, stmt);
                    } catch (FullMapException e) {
                        System.out.println("Symbol Table is full!");
                    }

                    opt = newString("Another CASE? (y/n)");
                    if (opt.equals("n")) {
                        break;
                    }
                }
                stmt = newStatement("Default Statement");
                return new SwitchStmt(varname, tbl, stmt);
            }
            if (choice.equals("New")) {
                String varname = newString("Varname");
                Exp exp = newExpression("Expression");
                return new NewStmt(varname, exp);
            }
            if (choice.equals("Write Heap")) {
                String varname = newString("Varname");
                Exp exp = newExpression("Expression");
                return new HeapWriteStmt(varname, exp);
            }
            if (choice.equals("Fork")) {
                IStmt stmt = newStatement("Statement");
                return new ForkStmt(stmt);
            }
            if (choice.equals("Open File")){
                String filename = newString("File name");
                return new OpenFile(filename);
            }
            if(choice.equals("Write File")){
                String filename = newString("File name");
                Exp exp = newExpression("Buffer");
                return new WriteFile(filename, exp);
            }
            if(choice.equals("Close File")){
                String filename = newString("File name");
                return new CloseFileStmt(filename);
            }

        }
        throw new IOException();

    }

    public Exp newExpression(String content) throws IOException {
        String[] items = {"Arithmetical", "Constant", "Variable", "Boolean", "Logical", "Read", "Read Heap"};
        ChoiceDialog choiceDialog = new ChoiceDialog(items[0], items);
        choiceDialog.setTitle("New Expression");
        choiceDialog.setContentText("Expression");
        Optional<String> result = choiceDialog.showAndWait();

        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals("Arithmetical")) {
                String operator = newString("Operator");
                Exp left = newExpression("Left operand");
                Exp right = newExpression("Right operand");
                return new ArithExp(left, right, operator);
            }
            if (choice.equals("Constant")) {
                int constant = Integer.parseInt(newString("Constant value"));
                return new ConstExp(constant);
            }
            if (choice.equals("Variable")) {
                String id = newString("Variable id");
                return new VarExp(id);
            }
            if (choice.equals("Boolean")) {
                String operator = newString("Operator");
                Exp left = newExpression("Left operand");
                Exp right = newExpression("Right operand");
                return new BoolExp(left, right, operator);
            }
            if (choice.equals("Logical")) {
                String operator = newString("Operator");
                Exp left = newExpression("Left operand");
                if (!operator.equals("!")) {
                    Exp right = newExpression("Right operand");
                    return new LogicalExp(left, right, operator);
                }
                return new LogicalExp(left, operator);
            }
            if (choice.equals("Read")) {
                int no = Integer.parseInt(newString("Constant value"));
                return new ReadExp(no);
            }
            if (choice.equals("Read Heap")) {
                String varname = newString("Varname");
                return new HeapReadExp(varname);
            }
        }
        throw new IOException();
    }

}
