# ProggDB-CandyCrush

Notes:

GameShape is an abstract class that will be extended by all the candies. This will make the interaction much more easier because we don't need to instantiate the specific candy every single time. We can just call the GameShape class, then the parameter passed will determine the candy itself. 

To generate a candy, you can use the following command:

```
GameShape.createRandom(width, height, size);
```

If you need a specific shape, you can simply modify GameShape file to have another method that takes a `ID` as additional input to generate the candy. The following IDs was set:
```
0: Potchi
1: Yema
2: Lala
3: Chocnut
4: Polvoron
5: hawHaw
6: WhiteRabbit
```
