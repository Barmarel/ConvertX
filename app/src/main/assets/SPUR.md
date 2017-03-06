- ЯКОРЯ -
^  [Начало строки]
$  [Конец строки]
\A [Начало текста]
\Z [Конец текста]
\< [Начало слова]
\> [Конец слова]

- СИМВОЛЫ -
\c    [Управляющий символ]
\s    [Пробел]
\S    [Не пробел]
\d    [Цифра]
\D    [Не цифра]
\w    [Слово]
\W    [Не слово]
\0xxx [8-ный символ xxx]
\xhh  [16-ный символ hh]

- СИМВОЛЫ POSIX -
[:upper:]  [Буквы в верхнем регистре]
[:lower:]  [Буквы в нижнем регистре]
[:alpha:]  [Все буквы]
[:alnum:]  [Все буквы и цифры]
[:word:]   [Буквы, цифры и подчёркивание]
[:digit:]  [Цифры]
[:xdigit:] [Цифры в 16-ной сист. счисления]
[:punct:]  [Пунктуация]
[:space:]  [Пустые символы]

- СТРОКИ -
$+ [Последняя найденная строка]
$& [Найденная строка целиком]
$_ [Текст целиком]
$$ [Символ $]

- ДИАПАЗОНЫ -
(a|b)  [a или b]
[abc]  [a, b, или с]
[^abc] [Не a, b, или с]
[0-4]  [Цифра в диапазоне от 0 до 4]
[C-X]  [Все буквы между C-X]
[C-x]  [Все буквы (игнор регистра) между C-x]
\n     [n-я группа]
(...)  [Логическая группа]

- КВАНТОРЫ -
*     [0 или больше]
+     [1 или больше]
?     [0 или 1]
{4}   [Ровно 4]
{4,}  [4 и больше]
{2,4} [2, 3 или 4]