package view;
import controller.Controller;
import controller.StatementExecutionException;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.Heap.LibHeap;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IList;
import domain.DataStructures.List.LibList;
import domain.DataStructures.Stack.EmptyStackException;
import domain.DataStructures.Stack.LibStack;
import domain.PrgState;
import domain.Stmt.*;
import domain.Expression.*;
import repository.EmptyRepository;
import repository.Repository;
import repository.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dutzi on 10/25/2015.
 */
public class Ui {
    private Controller ctrl;
    private Scanner reader;
   // private PrgState crtPrg;

    /**
     * @
     */
    public Ui() {
        //repo = new Repository();
        //ctrl = new Controller(repo);
        reader = new Scanner(System.in);
    }

    private String mainMenu = "\nMAIN MENU\n" +
            "1. Input a program\n" +
            "2. One-step evaluation\n" +
            "3. Execute program \n" +
            "4. Serialize program\n" +
            "5. Deserialize program\n" +
            "6. Write to file\n" +
            "0. Exit";
    private String statementMenu = "\nSTATEMENT MENU\n" +
            "1. Compound statement\n" +
            "2. Assign Statement\n" +
            "3. IF Statement\n" +
            "4. Print Statement\n" +
            "5. Increment Statement\n" +
            "6. While Statement\n" +
            "7. Switch Statement\n" +
            "8. IF Then Skip Statement\n" +
            "9. New Statement\n" +
            "10.Write Heap Statement\n";
    private String expressionMenu = "\nEXPRESSION MENU\n" +
            "1. Arithmetical expression\n" +
            "2. Constant expression\n" +
            "3. Variable expression\n" +
            "4. Relational expression\n" +
            "5. Logical expression\n" +
            "6. Read expression\n" +
            "7. Read Heap Expression\n";


    private void allStep() {

        try {
            ctrl.allStep();
        }
        catch (EmptyRepository e){
            print("No program state!");
        }
        catch (IOException e){
            print("IO");
        }
        catch (InterruptedException e){
            print("Interruption!");
        }
    }

    private void oneStep() {
        try{
            ctrl.oneStep();
        }
        catch (EmptyRepository e){
            print("No program state!");
        }
        catch (IOException e){
            print("IO");
        }
        catch (InterruptedException e){
            print("Interruption!");
        }
    }

    private void print(String message) {
        System.out.println(message);
    }

    private String readString(String message) {
        try {
            print(message);
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (IOException e) {
            print(e.getMessage());
        }
        return "";
    }

    private Integer readInteger(String message) throws InputDataTypeException {
        try {
            return Integer.parseInt(readString(message));
        } catch (NumberFormatException e) {
            throw new InputDataTypeException();
        }
    }

    private void toggleLogFlag(){
        if (ctrl.isLogFlag()){ print("Log mode: ON"); }
        else {print("Log mode OFF");}
        print("1. Change Log Flag");
        print("0. Do not change");
    }

    private void initialMenu()  {
        int option;
        System.out.println(mainMenu);
        try {
            option = readInteger("Choose an option:");
            //if (option != 0 ) {
            if (option != 1 && option != 5 && ctrl == null) {
                print("There is no program, please insert a program");
            }else{
                switch (option) {
                    case 1: {
                        inputProgram();
                        break;
                    }
                    case 2: {
                        this.toggleLogFlag();
                        try {
                            int op = readInteger("Choose: ");
                            if (op == 1) {
                                ctrl.changeLogFlag();
                            }
                            else{ break; }
                        } catch(InputDataTypeException e) {
                                print("Input invalid");
                        }
                        finally{
                            oneStep();
                            break;
                        }
                    }
                    case 3: {
                        this.toggleLogFlag();
                        try {
                            int op = readInteger("Choose: ");
                            if (op == 1) {
                                ctrl.changeLogFlag();
                            }
                            else{ break; }
                        } catch(InputDataTypeException e) {
                            print("Input invalid");
                        }
                        finally{
                            allStep();
                            break;
                        }
                    }
                    case 4: {
                        ctrl.getRepo().serialize();
                        print("Serialized!");
                        break;
                    }
                    case 5: {
                        List<PrgState> desStates;
                        desStates = ctrl.getRepo().deserialize();
                        break;
                    }
                    case 6: {
                        ctrl.getRepo().writeToFile();
                        break;
                    }
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option, please try again!\n");
                }
            }
            initialMenu();
        }
        catch (RepositoryException e){
            print("No program state added" );
        }
        catch (InputDataTypeException e){
            print("Invalid option.");
            initialMenu();
        }
        catch (IOException e){
            print("No program");
            initialMenu();
        }
        catch (ClassNotFoundException e)
        {
            print("Deserialization problems.");
            initialMenu();
        }
    }

    public void run() {
        initialMenu();


    }

    /**
     * @return
     */
    private CompStmt compStmt() {
        System.out.println("First statement: ");
        IStmt first = inputStatement();
        System.out.println("Second statement: ");
        IStmt second = inputStatement();
        return new CompStmt(first, second);
    }

    /**
     * @return
     */
    private AssignStmt assignStmt() throws InputDataTypeException {
        System.out.println("Var name:");
        String id = reader.next();
        System.out.println("Value: ");
        Exp expression = inputExpression();
        return new AssignStmt(id, expression);
    }

    /**
     * @return
     */
    private IfStmt ifStmt() {
        System.out.println("Expression: ");
        Exp expression = inputExpression();
        System.out.println("Then statement: ");
        IStmt thenS = inputStatement();
        System.out.println("Else statement: ");
        IStmt elseS = inputStatement();
        return new IfStmt(expression, thenS, elseS);
    }

    private IfSkipStmt ifSkipStmt() {
        System.out.println("Expression: ");
        Exp expression = inputExpression();
        System.out.println("Then statement: ");
        IStmt thenS = inputStatement();
        return new IfSkipStmt(expression, thenS);
    }

    /**
     * @return
     */
    private PrintStmt printStmt() {
        System.out.println("Expression: ");
        Exp expression = inputExpression();
        return new PrintStmt(expression);
    }

    private IncStmt incStmt(){
        System.out.println("Expression");
        Exp expression = inputExpression();
        return new IncStmt(expression);
    }


    private WhileStmt whileStmt(){
        System.out.println("While Expression: ");
        Exp exp = inputExpression();
        System.out.println("Statement");
        IStmt stmt = inputStatement();
        return new WhileStmt(exp, stmt);
    }

    private SwitchStmt switchStmt()throws InputDataTypeException{
        System.out.println("Varname: ");
        String varname = reader.next();
        Exp exp;
        String opt;
        IStmt stmt;
        IDictionary<Exp, IStmt> tbl = new LibDictionary<>();
        while(true){
            System.out.println("Expression (CASE): ");
            exp = inputExpression();
            System.out.println("Statement: ");
            stmt = inputStatement();
            try{
                tbl.add(exp, stmt);
            }
            catch (FullMapException e) {
                System.out.println("Symbol Table is full!");
            }

            System.out.println("Another CASE? (y/n)");
            opt = reader.next();
            if(opt.equals("n")){break;}
        }
        System.out.println("DEFAULT statement: ");
        stmt = inputStatement();
        return  new SwitchStmt(varname, tbl, stmt);
    }

    private NewStmt newStmt() {
        print("Varname:");
        String varname = reader.next();
        print("Expression:");
        Exp exp = inputExpression();
        return new NewStmt(varname, exp);
    }

    private HeapWriteStmt writeHeap(){
        print("Varname:");
        String varname = reader.next();
        print("Expression:");
        Exp exp = inputExpression();
        return new HeapWriteStmt(varname, exp);
    }


    private IStmt inputStatement() {
        int option;
        System.out.println(statementMenu);
        try {
            option = readInteger("Choose an option:");
            IStmt current;

            switch (option) { //comp stmt
                case 1: {
                    current = compStmt();
                    break;
                }
                case 2: { //assign stmt
                    current = assignStmt();
                    break;
                }
                case 3: {
                    current = ifStmt();
                    break;
                }
                case 4: {
                    current = printStmt();
                    break;
                }
                case 5: {
                    current = incStmt();
                    break;
                }
                case 6: {
                    current = whileStmt();
                    break;
                }
                case 7: {
                    current = switchStmt();
                    break;
                }
                case 8: {
                    current = ifSkipStmt();
                    break;
                }
                case 9: {
                    current = newStmt();
                    break;
                }
                case 10: {
                    current = writeHeap();
                    break;
                }
                default:
                    System.out.println("Invalid option! ");
                    current = inputStatement();
            }
            return current;
        }
        catch (InputDataTypeException e){
            System.out.println("Invalid input, try again!");
            return inputStatement();
        }
    }

    private ArithExp arithExp() throws InputDataTypeException {
        System.out.println("Operators: +, -, *, /\n");
        System.out.println("Operator: ");
        String operator = reader.next();
        System.out.println("Left operand: ");
        Exp left = inputExpression();
        System.out.println("Right operand");
        Exp right = inputExpression();
        return new ArithExp(left, right, operator);
    }

    private ConstExp constExp() throws InputDataTypeException {
        int constant = readInteger("Constant: ");
        return new ConstExp(constant);
    }

    private VarExp varExp() throws InputDataTypeException {
        System.out.println("Variable id: ");
        String id = reader.next();
        return new VarExp(id);
    }

    private BoolExp boolExp() throws InputDataTypeException {
        System.out.println("Operators: <, <=, >, >=, ==, !=\n");
        System.out.println("Operator:");
        String operator = reader.next();
        System.out.println("Left operand: ");
        Exp left = inputExpression();
        System.out.println("Right operand: ");
        Exp right = inputExpression();
        return new BoolExp(left, right, operator);
    }

    private  LogicalExp logicalExp() throws InputDataTypeException {
        System.out.println("Operators: &&, ||, !\n");
        System.out.println("Operator:");
        String operator = reader.next();
        System.out.println("Left operand: ");
        Exp left = inputExpression();
        if(!operator.equals("!")){
            System.out.println("Right operand: ");
            Exp right = inputExpression();
            return new LogicalExp(left, right, operator);
        }
        return new LogicalExp(left, operator);
    }

    private ReadExp readExp()throws InputDataTypeException {
        Integer no = readInteger("Introduce an integer for ToyLanguage ");
        return new ReadExp(no);
    }

    private HeapReadExp readHeap(){
        print("Varname:");
        String varname = reader.next();
        return new HeapReadExp(varname);
    }

    private Exp inputExpression(){
        int option;
        System.out.println(expressionMenu);
        try {
            Exp expression;
            option = readInteger("Choose an option: ");
            switch (option) {
                case 1: {
                    expression = arithExp();
                    break;
                }
                case 2: {
                    expression = constExp();
                    break;
                }
                case 3: {
                    expression = varExp();
                    break;
                }
                case 4: {
                    expression = boolExp();
                    break;
                }
                case 5: {
                    expression = logicalExp();
                    break;
                }
                case 6: {
                    expression = readExp();
                    break;
                }
                case 7: {
                    expression = readHeap();
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                    expression = inputExpression();
                }
            }
            return expression;
        }
        catch (InputDataTypeException e){
            print("Invalid input, try again!");
            return inputExpression();
        }
    }

    private void inputProgram() throws RepositoryException, IOException {

        //IStmt prgStatement = inputStatement();
        LibStack stk = new LibStack<>();
        LibDictionary dict = new LibDictionary<>();
        LibList lst = new LibList<>();
        LibHeap heap = new LibHeap<>();
        LibDictionary<Exp, IStmt> tbl = new LibDictionary<>();

//
//        v=10;new(a,22);
//        fork(wH(a,30);
//         v=32;
//          print(v);
//          print(rH(a)));
//        print(v);
//          print(rH(a))

        IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("a", new ConstExp(22));
        IStmt st3 = new AssignStmt("v", new ConstExp(32));
        IStmt st4 = new PrintStmt(new VarExp("v"));
        IStmt st5 = new PrintStmt(new HeapReadExp("a"));
        IStmt st8 = new ForkStmt(new CompStmt(new HeapWriteStmt("a", new ConstExp(30)), new CompStmt(st3, new CompStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExp("v"));
        IStmt st7 = new PrintStmt(new HeapReadExp("a"));
        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6, st7))));



        List<PrgState> prgStates = new ArrayList<>();

        PrgState prgS = new PrgState(1, stk,  dict, lst, heap, prgStatement);
        prgStates.add(prgS);
        print(prgStates.toString());


        ctrl = new Controller(new Repository(prgStates));
        ctrl.getRepo().serialize();

    }
}
