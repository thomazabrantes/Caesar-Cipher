#################################################################################################
###  Divisão serial  $a1/ $a0 -->   $v0--> resto    $v1 --> divisão
################################################################################################
division:
          lui   $t0, 0x8000       # máscara para isolar bit mais significativo
          li    $t1, 32           # contador de iterações

          xor   $v0, $v0, $v0     # registrador P($v0)-A($v1) com  0 e o dividendo ($a1)
          add   $v1, $a1, $0

dloop:    and   $t2, $v1, $t0     # isola em t2 o bit mais significativo do registador 'A' ($v1)
          sll   $v0, $v0, 1       # desloca para a esquerda o registrado P-A
          sll   $v1, $v1, 1 

          beq   $t2, $0, di1    
          ori   $v0, $v0, 1       # coloca 1 no bit menos significativo do registador 'P'($v0)

di1:      sub   $t2, $v0, $a0     # subtrai 'P'($v0) do divisor ($a0)
          blt   $t2, $0, di2
          add   $v0, $t2, $0      # se a subtração deu positiva, 'P'($v0) recebe o valor da subtração
          ori   $v1, $v1, 1       # e 'A'($v1) recebe 1 no bit menos significativo

di2:      addi  $t1, $t1, -1      # decrementa o número de iterações 
          bne   $t1, $0, dloop 

          jr    $ra  