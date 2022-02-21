### French and English SOUNDEX

---

I tried to make a java copy of [this project](https://github.com/equipe22/phonicsFR), since the [article from Florent Carlier](http://info.univ-lemans.fr/~carlier/recherche/soundex.html) is unavailable.  
The ***basic*** English and French implementation are from wikipedia: [https://fr.wikipedia.org/wiki/Soundex](https://fr.wikipedia.org/wiki/Soundex).
    
#### To test it with a file dictionary:  
>`$ cd soundex`  
`$ mvn clean install`   
`$ java -cp target/soundex-1.0-SNAPSHOT.jar soundex.Example ../(fr|eng)_list.txt (fr|basic_fr|basic_eng)`





 
