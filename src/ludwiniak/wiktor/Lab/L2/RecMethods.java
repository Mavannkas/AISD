package ludwiniak.wiktor.Lab.L2;

public class RecMethods {
    public static void printValue(LinkedElement e){
        if(e != null){
            System.out.println(e.element);
            printValue(e.next);
        }
    }
    public static void printValueFromEnd(LinkedElement e){
        if(e != null){
            printValueFromEnd(e.next);
            System.out.println(e.element);
        }
    }

    public static int summRec(LinkedElement<Integer> e){
        if(e.next != null){
            return e.element + summRec(e.next);
        }else{
            return e.element;
        }

    }

    public static int findBiggestNumber(LinkedElement<Integer> e){
        if(e.next != null){
            return Math.max(e.element, findBiggestNumber(e.next));
        }else{
            return e.element;
        }
    }
    public static int findSmallestNumber(LinkedElement<Integer> e){
        if(e.next != null){
            return Math.min(e.element, findSmallestNumber(e.next));
        }else{
            return e.element;
        }
    }
}
