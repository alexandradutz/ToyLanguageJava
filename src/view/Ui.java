package view;
import controller.Controller;
import controller.StatementExecutionException;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IList;
import domain.DataStructures.List.LibList;
import domain.DataStructures.Stack.LibStack;
import domain.PrgState;
import domain.Stmt.*;
import domain.Expression.*;
import repository.Repository;
import repository.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Dutzi on 10/25/2015.
 */
public class Ui {
    private Controller ctrl;
    private Scanner reader;
    private PrgState crtPrg;

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
            "8. IF Then Skip Statement\n";
    private String expressionMenu = "\nEXPRESSION MENU\n" +
            "1. Arithmetical expression\n" +
            "2. Constant expression\n" +
            "3. Variable expression\n" +
            "4. Relational expression\n" +
            "5. Logical expression\n" +
            "6. Read expression\n";


    private void allStep() {

        try{
            ctrl.allStep("log.txt");
        }catch (StatementExecutionException e){
            System.out.println("\nFINISHED\n");
            ctrl.getRepo().serialize();
            crtPrg = null;
        }
        catch (VariableNotDefinedException e){
            System.out.println("A variable is not defined!");
        }
        catch (DivisionByZeroException e){
            System.out.println("Division by zero;");
        }
        catch (FullListException e){
            System.out.println("There's no room for another output!");
        }
        catch (FullMapException e){
            System.out.println("Symbol Table is full!");
        }
        catch (IsNotKeyException e){
            System.out.println("There is no such key!");
        }
        catch (RepositoryException e){
            print("No program state!");
        }
        catch (IOException e){
            print("IO");
        }
    }

    private void oneStep() {
        try{
            ctrl.oneStep("log.txt");
        } catch (StatementExecutionException e){
            System.out.println("\nFINISHED\n");
            ctrl.getRepo().serialize();
            crtPrg = null;
        }
        catch (VariableNotDefinedException e){
            System.out.println("A variable is not defined!");
        }
        catch (DivisionByZeroException e){
            System.out.println("Division by zero;");
        }
        catch (FullListException e){
            System.out.println("There's no room for another output!");
        }
        catch (FullMapException e){
            System.out.println("Symbol Table is full!");
        }
        catch (IsNotKeyException e) {
            System.out.println("There is no such key!");
        }
        catch (IOException e){
            print("IO");
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
            if (option != 0 ) {
                switch (option) {
                    case 1: {
                        inputProgram();
                        //ctrl.getRepo().example2();
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
                            if (ctrl.isLogFlag()) { ctrl.getRepo().writeToFile("log.txt"); }
                            else { print(ctrl.getRepo().getCrtPrg().toStr()); }
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
                            if (ctrl.isLogFlag()) { ctrl.getRepo().writeToFile("log.txt"); }
                            else { print(ctrl.getRepo().getCrtPrg().toStr()); }
                            break;
                        }
                    }
                    case 4: {
                        ctrl.getRepo().serialize();
                        print("Serialized!");
                        break;
                    }
                    case 5: {
                        IList<PrgState> desStates;
                        desStates = ctrl.getRepo().deserialize();
                        print(desStates.toString());
                        break;
                    }
                    case 6: {
                        ctrl.getRepo().writeToFile("log.txt");
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
    /**
     * @return
     */
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
        IStmt prgStatement = new CompStmt(new AssignStmt("a", new ArithExp(new ReadExp(0), new ConstExp(2), "-")),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)),
                        new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));

        //IStmt programStmt = inputStatement();
        LibStack stk = new LibStack<>();
        LibDictionary dict = new LibDictionary<>();
        LibList lst = new LibList<>();

        IList<PrgState> prgStates = new LibList<>();
        PrgState prgS = new PrgState(stk,  dict, lst, prgStatement);
        try{
            prgStates.add(prgS);
        }catch (FullListException e)
        {
            System.out.println("Full list");
        }

        ctrl = new Controller(new Repository(prgStates));
        crtPrg = ctrl.getRepo().getCrtPrg();
        print(crtPrg.toStr());
        //ctrl.getRepo().serialize();

    }
}
