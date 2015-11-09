package view;

import controller.Controller;
import controller.StatementExecutionException;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.LibList;
import domain.DataStructures.Stack.LibStack;
import domain.PrgState;
import domain.Stmt.*;
import domain.Expression.*;
import repository.IRepository;
import repository.Repository;

import java.util.Scanner;

/**
 * Created by Dutzi on 10/25/2015.
 */
public class Ui {
    private Controller ctrl;
    private IRepository repo;
    private Scanner reader;
    private PrgState crtPrg;

    /**
     * @
     */
    public Ui() {
        repo = new Repository();
        ctrl = new Controller(repo);
        reader = new Scanner(System.in);
    }

    private String mainMenu = "\nMAIN MENU\n" +
            "1. Input a program\n" +
            "2. Print program\n" +
            "3. One-step evaluation\n" +
            "4. Complete evaluation (run)\n" +
            "5. Toggle debug flag\n" +
            "0. Exit";
    private String statementMenu = "\nSTATEMENT MENU\n" +
            "1. Compound statement\n" +
            "2. Assign Statement\n" +
            "3. IF Statement\n" +
            "4. Print Statement\n" +
            "5. Increment Statement\n" +
            "6. While Statement\n" +
            "7. Switch Statement\n";
    private String expressionMenu = "\nEXPRESSION MENU\n" +
            "1. Arithmetical expression\n" +
            "2. Constant expression\n" +
            "3. Variable expression\n" +
            "4. Relational expression\n" +
            "5. Logical expression\n" +
            "6. Read expression\n";


    private void allStep() {

        try{
            ctrl.allStep();
        }catch (StatementExecutionException e){
            System.out.println("\nFINISHED\n");
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
    }

    private void oneStep() {
        try{
            ctrl.oneStep(ctrl.getRepo().getCrtPrg());
        } catch (StatementExecutionException e){
        System.out.println("\nFINISHED\n");
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
    }

    private void printPrg() {
        System.out.println(ctrl.getRepo().getCrtPrg().toStr());
    }

    private int readInt() throws InputDataTypeException{
        return reader.nextInt();
    }

    private void initialMenu() {
        int option;
        System.out.println(mainMenu);
        System.out.println("Choose an option:");
        try {
            option = readInt();
            if (option != 0 && crtPrg == null) {
                switch (option) {
                    case 1: {
                        inputProgram();
                        //ctrl.getRepo().example1();
                        //ctrl.getRepo().example2();
                        initialMenu();
                        break;
                    }
                    case 2: {
                        printPrg();
                        initialMenu();
                        break;
                    }
                    case 3: {
                        oneStep();
                        initialMenu();
                        break;
                    }
                    case 4: {
                        allStep();
                        initialMenu();
                        break;
                    }
                    case 5: {
                        ctrl.changeDebugFlag();
                        System.out.println("Debug mode: " + ctrl.isDebugFlag() + "\n");
                        initialMenu();
                        break;
                    }
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option, please try again!\n");
                }
            }
        }
        catch (InputDataTypeException e){
            System.out.println("Invalid option. Insert a number!");
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
        System.out.println("Choose an option:");
        try {
            option = readInt();
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
                default:
                    System.out.println("Invalid option! ");
                    current = inputStatement();
            }
            return current;
        }
        catch (InputDataTypeException e){
            System.out.println("Invalid input, try again!");
        }
        return inputStatement();
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
        System.out.println("Constant: ");
        int constant = readInt();
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
        System.out.println("Introduce an integer for ToyLanguage ");
        Integer no = readInt();
        return new ReadExp(no);
    }

    private Exp inputExpression() {
        int option;
        System.out.println(expressionMenu);
        System.out.println("Choose an option: ");
        try {
            Exp expression;
            option = readInt();
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
            System.out.println("Invalid input, try again!");
        }
        return inputExpression();
    }

    private void inputProgram() {
        IStmt programStmt = inputStatement();
        /**        */
        LibStack stk = new LibStack<>();
        LibDictionary dict = new LibDictionary<>();
        LibList lst = new LibList<>();
         //stk.push(programStmt);

        PrgState crtPrg = new PrgState(stk,  dict, lst, programStmt);

        ctrl.getRepo().setCrtPrg(crtPrg);
    }
}
