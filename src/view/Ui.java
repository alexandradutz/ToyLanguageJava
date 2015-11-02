package view;

import controller.Controller;
import domain.DataStructures.ArrayDictionary;
import domain.DataStructures.ArrayList;
import domain.DataStructures.ArrayStack;
import domain.Expression.ArithExp;
import domain.Expression.ConstExp;
import domain.Expression.Exp;
import domain.Expression.VarExp;
import domain.PrgState;
import domain.Stmt.*;
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
            "6. For Statement\n" +
            "7. While Statement\n";
    private String expressionMenu = "\nEXPRESSION MENU\n" +
            "1. Arithmetical expression\n" +
            "2. Constant expression\n" +
            "3. Variable expression\n";


    private void allStep() {
        ctrl.allStep();
    }

    private void oneStep() {
        ctrl.oneStep(ctrl.getRepo().getCrtPrg());
    }

    private void printPrg() {
        System.out.println(ctrl.getRepo().getCrtPrg().toStr());
    }

    /**
     * @throws Exception
     */
    private void initialMenu() {
        int option;
        System.out.println(mainMenu);
        System.out.println("Choose an option:");
        option = reader.nextInt();

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
    private AssignStmt assignStmt() {
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

    private ForStmt forStmt(){
        System.out.println("Expression: (first index)");
        Exp exp1 = inputExpression();
        System.out.println("Expression: (last index)");
        Exp exp2 = inputExpression();
        System.out.println("Expression: (step size)");
        Exp exp3 = inputExpression();
        System.out.println("Statement:");
        IStmt stmt = inputStatement();
        return new ForStmt(exp1, exp2, exp3, stmt);
    }

    private WhileStmt whileStmt(){
        System.out.println("While Expression: ");
        Exp exp = inputExpression();
        System.out.println("Statement");
        IStmt stmt = inputStatement();
        return new WhileStmt(exp, stmt);
    }

    /**
     * @return
     */
    private IStmt inputStatement() {
        int option;
        System.out.println(statementMenu);
        System.out.println("Choose an option:");
        option = reader.nextInt();
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
                current = forStmt();
                break;
            }
            case 7: {
                current = whileStmt();
                break;
            }
            default:
                System.out.println("Invalid option! ");
                current = inputStatement();
        }
        return current;
    }

    private ArithExp arithExp() {
        System.out.println("Operators: +, -, *, /\n");
        System.out.println("Operator: ");
        String operator = reader.next();
        System.out.println("Left operand: ");
        Exp left = inputExpression();
        System.out.println("Right operand");
        Exp right = inputExpression();
        return new ArithExp(left, right, operator);
    }

    private ConstExp constExp() {
        System.out.println("Constant: ");
        int constant = reader.nextInt();
        return new ConstExp(constant);
    }

    private VarExp varExp() {
        System.out.println("Variable id: ");
        String id = reader.next();
        return new VarExp(id);
    }

    private Exp inputExpression() {
        int option;
        System.out.println(expressionMenu);
        System.out.println("Choose an option: ");
        option = reader.nextInt();
        Exp expression;

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
            default: {
                System.out.println("Invalid option!");
                expression = inputExpression();
            }
        }
        return expression;
    }

    private void inputProgram() {
        IStmt programStmt = inputStatement();
        ArrayStack stk = new ArrayStack();
        ArrayDictionary dict = new ArrayDictionary();
        ArrayList lst = new ArrayList();

        stk.push(programStmt);
        PrgState crtPrg = new PrgState(stk, dict, lst);

        ctrl.getRepo().setCrtPrg(crtPrg);

        if (ctrl.getRepo().getCrtPrg().getExeStack().isEmpty()) {
            System.out.println("No input program. Exe Stack IS empty, in UI!");
        } else {
            System.out.println("Exe Stack IS NOT empty, in UI");

            System.out.println(crtPrg.toStr());

        }

    }
}
