graph get.csv -x "Param: size" -y "Score" --xlabel "Number of operation" --ylabel "Milliseconds" --title "get" -o get.png --fontsize 10 --marker " "

graph put.csv -x "Param: size" -y "Score" --xlabel "Number of operation" --ylabel "Milliseconds" --title "put" -o put.png --fontsize 10 --marker " "

graph remove.csv -x "Param: size" -y "Score" --xlabel "Number of operation" --ylabel "Milliseconds" --title "remove" -o remove.png --fontsize 10 --marker " "

graph get.csv -x "Param: size" -y "Score" --xlabel "Number of operation" --ylabel "Milliseconds" --fontsize 10 -l "get" --marker " " --chain | graph put.csv -x "Param: size" -y "Score" -l "put" --marker " " --chain | graph remove.csv -x "Param: size" -y "Score" -l "remove" --marker " " --title "Closed Hashtable" -o sample.png