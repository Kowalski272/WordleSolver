# WordleSolver

Hello! This is the source code of a Wordle Solver I made in Java. To get started you'll need to modify the first line in Main to import the dictionary I used (referenced here as USA.txt). 
After executing the code it'll ask you for your first guess and the corresponding output from the Wordle game. The output is a continuous string corresponding to the colored output from Wordle.

Green = G, Y = Yellow and X = Gray

Ex: Inputted Word Arose, (A,R,O,E) were gray and S was yellow

Corresponding Output to Submit to the Prompt: (XXXYX)

From there the program will suggest the top 3 words based on frequency and highest probability of the remaining words left to try.
In this example the highest suggested word was "spilt"

Ex: Input word Spilt, (S,P,I,L) were green and T was gray

Corresponding Output to Submit to the Prompt: (GGGGX)

Continue inputting words until the Wordle is solved!

General Notes:
Currently the program only accepts lower-case inputs. Capitalizing the first letter/any letters will request you resubmit an input (will be fixed in later versions)
The program returns the top 3 words based on a calculated score. The score is determined by the frequency the letters show up throughout the entire dictionary.
Higher scores are assigned to words without repeating words to help thin out the dictionary.
