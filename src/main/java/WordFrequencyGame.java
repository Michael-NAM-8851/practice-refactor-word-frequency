import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){


        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split("\\s+");

                List<Input> inputList = Arrays.stream(arr)
                        .map(word -> new Input(word, 1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);

//                List<Input> list = new ArrayList<>();
//                for (Map.Entry<String, List<Input>> entry : map.entrySet()){
//                    Input input = new Input(entry.getKey(), entry.getValue().size());
//                    list.add(input);
//                }
                inputList = map.entrySet()
                        .stream()
                        .map( word ->  new Input(word.getKey(), word.getValue().size()))
                        .collect(Collectors.toList());

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return inputList.stream()
                        .map(word -> word.getValue() + " " + word.getWordCount())
                        .collect(Collectors.joining("\n"));
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = inputList
                .stream()
                .collect(Collectors.groupingBy(Input::getValue));
        return map;
    }


}
