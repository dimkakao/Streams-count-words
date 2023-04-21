package com.epam.rd.autotasks;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {

        Map<String,Integer> map = new TreeMap<>();
        lines.stream()
                .flatMap(x -> Arrays.stream(x.toLowerCase().split("[\"! .$%˘&()*+,-./™ˇ•:;–—<=>?@\\[\\\\\\]^_`’”“{}|‘‡‚†…„~€]+|('s)")))
                .filter(x -> x.length() >= 4)
                .forEach( x-> {
                    map.computeIfPresent( x,(y,z)-> map.get(x)+1);
                    map.putIfAbsent(x, 1);
                });

        StringBuilder sb = new StringBuilder();
        LinkedList<Map.Entry<String,Integer>> resList = new LinkedList<>(List.copyOf(map.entrySet()));
        resList.sort((o1,o2)->  o2.getValue().compareTo(o1.getValue()));

        resList.stream()
                .filter(x -> x.getValue() >= 10)
                .forEach(x-> sb.append(x.getKey()).append(" - ").append(x.getValue()).append("\n"));

        sb.deleteCharAt(sb.lastIndexOf("\n"));
        return sb.toString();
    }
}
