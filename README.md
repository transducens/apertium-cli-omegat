# apertium-cli-omegat
Apertium command line interface for OmegaT

Compile and package with 

```
ant jar
```

(or download the jar from the build folder) and copy build/jar/apertium-cli-omegat.jar and the contents of lib into the plugins folder of OmegaT.


##Configuration

1. Local Apertium should be the same command for Apertium as used in a terminal emulator. If you installed Apertium through the official repository or through make install, it can be just apertium; else the full path should be included

2. Show unknown can be enabled to show those suggestions that include unknown words (*), lexical transfer problems (@), words with incorrect target-language lexical units (#) or words that need post-generation (~). All these symbols will be removed from the suggestions.

3. Working directory works like apertium -d /working/directory. If your language pair is installed through the official repository or through make install, the text box may be left empty.

4. After correctly setting Local Apertium and the Working directory properties, Update language pairs can be used to refresh the Language pair drop down menu. The combobox will contain the different modes found in the default location (or in the Working directory if you set one). Most Apertium language pairs do not use two-letter OmegaT language codes, like EN or EN_US, so you will have to manually select the mode you want to use, that will be used regardless of the project settings.

##Coming soon...

A mapping between OmegaT 2 (EN) and 4 letter (EN_US) language codes to Apertium 3 letter language codes. 
