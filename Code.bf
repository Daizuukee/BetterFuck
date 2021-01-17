clone{delete(9999)0*>[-0*<1*>+9999>+9999<1*<0*>]0*<1*>9999>[-9999<1*<0*>+0*<9999>1*>]9999<1*<} /clones a value*/
move{0*>[-0*<1*>+1*<0*>]0*<} /moves a value*/
delete{0*>[-]0*<} /*Deletes a value*/
set{delete(0*)0*>1*+0*<} /*Sets the value at 0* to 1* */

notzero{0*>[-0*<10000>+10000<0*>[-]]0*<move(10000;0*)} /*For the not() command*/
less{clone(0*;4001)clone(1*;4000)delete(0*)delete(1*)4000>+>+<[-[>-[< <-<]<[-]>>+<<]>[-]>>]4003<clone(4002;2*)delete(4002)} /*Atr0x algorithm*/
not{10000>+10000<0*>[-0*<10000>-10000<0*>]0*<move(10000;0*)}

add{0*>[-0*<2*>+2*<0*>]0*<1*>[-1*<2*>+2*<1*>]1*<}
subtract{0*>[-0*<2*>+2*<0*>]0*<1*>[-1*<2*>-2*<1*>]1*<}

multiply{0*>[-0*<clone(1*;2*)0*>]0*<delete(1*)}


pow{
    clone(1*;11000)
    11000>[11000<
        2*>+2*<
        1*>-1*<
    11000>[-]]11000<
    1*>[-1*<
        clone(0*;11000)multiply(2*;11000;11001)clone(11001;2*)delete(11001)
    1*>]1*<
    delete(0*)
}

divide{
    delete(1002)delete(1003)
    clone(0*;1002)clone(1*;1003)
    delete(1001)less(1002;1003;1001)not(1001)
    1001>[1001<
        clone(1*;1003)
        subtract(0*;1003;1002)clone(1002;0*)
        delete(1002)delete(1003)
        2*>+2*<
        clone(0*;1002)clone(1*;1003)
        delete(1001)less(1002;1003;1001)not(1001)
        delete(1002)delete(1003)
    1001>]1001<
    delete(0*)delete(1*)
}
/* Modulo */
rest{
    delete(1002)delete(1003)
    clone(0*;1002)clone(1*;1003)
    delete(1001)less(1002;1003;1001)not(1001)
    1001>[1001<
        clone(1*;1003)
        subtract(0*;1003;1002)clone(1002;0*)
        delete(1002)delete(1003)
        clone(0*;1002)clone(1*;1003)
        delete(1001)less(1002;1003;1001)not(1001)
        delete(1002)delete(1003)
    1001>]1001<
    move(0*;2*)
    delete(0*)delete(1*)
}

/* Displays a number from 0 to 255 */
display{
    delete(12001)set(12001;10)
    clone(0*;12000)
    12000>[12000<
        clone(12001;12002)divide(12000;12002;12003)clone(12003;12000)delete(12003)12004>+12004<
    12000>]12000<

    clone(0*;12000)notzero(12000)not(12000)
    12000>[12000<
        12000>47+,47-12000<
    12000>[-]]12000<

    clone(0*;12000)
    clone(12001;12002)
    clone(12004;12005)
    pow(12002;12005;12003)
    move(12003;12002)

    12004>[-12004<
        clone(12000;12005)clone(12002;12006)divide(12005;12006;12007)clone(12001;12006)rest(12007;12006;12005)
        12005>48+,12005<
        delete(12005)delete(12006)delete(12007)
        clone(12002;12005)clone(12001;12006)delete(12002)divide(12005;12006;12002)
    12004>]12004<
    delete(12000)delete(12001)delete(12002)delete(12003)delete(12004)delete(12005)delete(12006)delete(12007)
}

newline{9999>10+,10-9999<}
char{9999>64+0*+,0*-64-9999<}
space{9999>32+,32-9999<}
test{0*>debug()0*<}

main{
    .display(0)>.display(0) /*INPUT*/
    <
    newline()
    clone(0;10)
    clone(1;11)
    add(10;11;2)
    char(1)char(4)char(4)space()display(2)newline() /*ADD */
    clone(0;10)
    clone(1;11)
    subtract(10;11;3)
    char(19)char(21)char(2)char(20)char(18)char(1)char(3)char(20)space()display(3)newline() /*SUBTRACT */
    clone(0;10)
    clone(1;11)
    multiply(10;11;4)
    char(13)char(21)char(12)char(20)char(9)char(12)char(25)space()display(4)newline() /*MULTIPLY */
    clone(0;10)
    clone(1;11)
    divide(10;11;5)
    char(4)char(9)char(22)char(9)char(4)char(5)space()display(5)newline() /*DIVIDE */
} /*Main Code*/
