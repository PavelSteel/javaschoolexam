package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        boolean mark = true;
        try{
            if (x==null||y==null)
                throw new NullPointerException();
            else if (x.equals(y))
                return true;
            else if (x.size()==0)
            {
                mark = false;
                return true;
            }
            else if (mark&&(x.size()<=y.size())){
                boolean b = false;
                boolean marker = true;
                List list = new ArrayList();
                int[] arr = new int[x.size()];
                for (int i = 0; i < x.size(); i++){
                    for (int j = 0; j < y.size(); j++){
                        if (x.get(i).equals(y.get(j)))
                        {
                            list.add(y.get(j));
                            arr[i]=j;
                            break;
                        }
                    }
                }
                for (int i = 0; i < arr.length-1; i++)
                {
                    if (arr[i]>arr[i+1])
                    {
                        b = false;
                        marker = false;
                        break;
                    }
                }
                if (marker&&list.isEmpty())
                    b = false;
                else if (marker&&(x.size()==list.size()&&x.equals(list)))
                    b = true;
                else if (marker&&eq(x,list))
                    b = true;
                return b;
            }
            else if ((x.isEmpty() && !y.isEmpty()) || !x.isEmpty() && y.isEmpty())
                return false;
            else
                return false;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    public boolean eq(List a, List b) {
        int z = 0;
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < b.size(); j++) {
                    if (a.get(i).equals(b.get(j))) {
                        z++;
                    }
                }
            }
        }
        if (z == b.size())
            return true;
        else
            return false;
    }
}

