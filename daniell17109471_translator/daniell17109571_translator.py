import xlrd
import sys

translating = raw_input("Please enter something: ")

book = xlrd.open_workbook("LanguageValues.xls")
sh = book.sheet_by_index(0)
langs = {}
letters = {}
for x in range(1,sh.ncols):
    langs.update({x:str(sh.cell_value(0,x))})
for y in range(1,sh.nrows):
    letters.update({str(sh.cell_value(y,0)):y})

translating = translating.lower()

lettervalues = {}#(letter in given phrase: percent occurence)

numspaces = 0
for x in range(0, len(translating)):#Recording the amount of times each letter appears in the phrase
    letter = str(translating[x])
    if letter in letters.keys():
        if letter in lettervalues:
            lettervalues[letter]=lettervalues[letter]+1
        else:
            lettervalues.update({letter:1})
numletters = 0
for x in range(0,len(lettervalues)):
    numletters = numletters + lettervalues.values()[x]
letterlist = lettervalues.keys()#all the letters that occur in the tested string

for x in range(0,len(letterlist)):#converting from times of appearence to percent appearence
    lettervalues[letterlist[x]]=float(lettervalues[letterlist[x]])/float(numletters)*100
    
manhattan={}#manhattan values of each language
for x in range (1,sh.ncols):
    manhattann = 0.0
    #x is the column number of the language
    for y in range(0,len(letterlist)):
        #y is the index value of a letter in letterlist
        lettertested = str(letterlist[y])
        manhattann = manhattann + abs(lettervalues[lettertested]-float(sh.cell_value(letters[lettertested],x)))
    manhattan[manhattann] = langs[x]
print manhattan[min(manhattan.keys())]
