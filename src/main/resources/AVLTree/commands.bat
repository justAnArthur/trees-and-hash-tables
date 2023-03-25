
graph .\insert_random.csv -x "Score" -y "Param: size" -l "insert_random" --fontsize 8 --chain | graph .\insert_sorted.csv -x "Score" -y "Param: size" -l "insert_sorted" --chain | graph .\insert_to_size.csv -x "Score" -y "Param: size" -l "insert_to_size" --chain | graph .\delete.csv -x "Score" -y "Param: size" -l "delete" --chain | graph .\search.csv -x "Score" -y "Param: size" -l "search" --xlabel "Milliseconds" --ylabel "Number of operation" --title "AVL" -o .\graph.png


graph .\insert_random.csv -x "Score" -y "Param: size" -l "insert_random"  --xlabel "Milliseconds" --ylabel "Number of operation" --title "insert_random" --fontsize 14 -o .\insert_random.png

graph .\insert_sorted.csv -x "Score" -y "Param: size" -l "insert_sorted"  --xlabel "Milliseconds" --ylabel "Number of operation" --title "insert_sorted" --fontsize 14 -o .\insert_sorted.png

graph .\insert_to_size.csv -x "Score" -y "Param: size" -l "insert_to_size"  --xlabel "Milliseconds" --ylabel "Number of operation" --title "insert_to_size" --fontsize 14 -o .\insert_to_size.png


graph .\delete.csv -x "Score" -y "Param: size" -l "delete"  --xlabel "Milliseconds" --ylabel "Number of operation" --title "delete" --fontsize 14 -o .\delete.png


graph .\search.csv -x "Score" -y "Param: size" -l "search"  --xlabel "Milliseconds" --ylabel "Number of operation" --title "search" --fontsize 14 -o .\search.png