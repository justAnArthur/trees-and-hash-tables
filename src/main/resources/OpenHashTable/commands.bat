graph .\get.csv -x "Score" -y "Param: size" --xlabel "Milliseconds" --ylabel "Number of operation" --title "OpenHashTable_get" -o .\get.png --fontsize 10

graph .\put.csv -x "Score" -y "Param: size" --xlabel "Milliseconds" --ylabel "Number of operation" --title "OpenHashTable_put" -o .\put.png --fontsize 10

graph .\remove.csv -x "Score" -y "Param: size" --xlabel "Milliseconds" --ylabel "Number of operation" --title "OpenHashTable_remove" -o .\remove.png --fontsize 10

graph .\get.csv -x "Score" -y "Param: size" -l "insert_random" --fontsize 8 --chain | graph .\put.csv -x "Score" -y "Param: size" -l "delete" --chain | graph .\remove.csv -x "Score" -y "Param: size" -l "search" --xlabel "Milliseconds" --ylabel "Number of operation" --title "OpenHashTable" -o .\graph.png
