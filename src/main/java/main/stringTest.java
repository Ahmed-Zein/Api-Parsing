package main;

import java.util.ArrayList;

class StringTest {
    public static void main(String[] args) {
        ArrayList<BigObject> obs = new ArrayList<>();

        BigObject bgObj = new BigObject();
        String objString = "/object1/object2/field5";
        String[] tst = objString.split("/");
        if (tst[1].startsWith("object")) {
            bgObj.setName(tst[1]);
        }
        if (tst[2].startsWith("field")) {
            bgObj.addField(new Field(tst[2]));
        }
        if (tst[2].startsWith("object")) {
            bgObj.addField(new Field(tst[2]));
        }
       System.out.println(bgObj.getName()); 
       System.out.println(bgObj.getField(0).getName()); 
    }
}