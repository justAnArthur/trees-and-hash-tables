
graph .\insert_random.csv -x "Score" -y "Param: size" -l "insert_random" --fontsize 8 --chain | graph .\insert_sorted.csv -x "Score" -y "Param: size" -l "insert_sorted" --chain | graph .\insert_to_size.csv -x "Score" -y "Param: size" -l "insert_to_size" --chain | graph .\delete.csv -x "Score" -y "Param: size" -l "delete" --chain | graph .\search.csv -x "Score" -y "Param: size" -l "search" --xlabel "Milliseconds" --ylabel "Number of operation" --title "AVL" -o .\graph.png


graph insert_random.csv -x "Param: size" -y "Score" -l "insert_random" --xlabel "Number of operation" --ylabel "Milliseconds" --title "insert_random" --fontsize 14 --marker '' --figsize 1600x1000 -o insert_random.png

graph insert_sorted.csv -x "Param: size" -y "Score" -l "insert_sorted" --xlabel "Number of operation" --ylabel "Milliseconds" --title "insert_sorted" --fontsize 14 --marker '' --figsize 1600x1000 -o insert_sorted.png

graph insert_to_size.csv -x "Param: size" -y "Score" -l "insert_sorted" --xlabel "Number of operation" --ylabel "Milliseconds" --title "insert_to_size" --fontsize 14 --marker '' --figsize 1600x1000 -o insert_to_size.png

graph delete.csv -x "Param: size" -y "Score" -l "insert_sorted" --xlabel "Number of operation" --ylabel "Milliseconds" --title "delete" --fontsize 14 --marker '' --figsize 1600x1000 -o delete.png

graph search.csv -x "Param: size" -y "Score" -l "insert_sorted" --xlabel "Number of operation" --ylabel "Milliseconds" --title "search" --fontsize 14 --marker '' --figsize 1600x1000 -o search.png


graph insert_random.csv -x "Param: size" -y "Score" -l "insert_random" --xlabel "Number of operation" --ylabel "Milliseconds" --fontsize 14 --marker '' --figsize 1600x1000 -l "insert_random" --chain | graph insert_sorted.csv -x "Param: size" -y "Score" --marker '' -l "insert_sorted" --chain | graph insert_to_size.csv -x "Param: size" -y "Score" --marker '' -l "insert_to_size" --chain | graph delete.csv -x "Param: size" -y "Score" --marker '' -l "delete" --chain | graph search.csv -x "Param: size" -y "Score" --marker '' -l "search" --title "AVL" -o sample.png
