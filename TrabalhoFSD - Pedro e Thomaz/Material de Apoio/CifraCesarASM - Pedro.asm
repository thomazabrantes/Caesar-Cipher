.text
.globl main
#################################################################################################
###   $s0 -- > matricula;   $s1 -->  dig_verif;   $s2-->  size;   $s3 --> encrypted
################################################################################################
main:
    # Carregar os valores de matricula, dig_verif e size
    la   $t0, matricula
    lw   $s0, 0($t0)     			# $s0 = matricula
    
    la   $t1, dig_verif
    lw   $s1, 0($t1)      			# $s1 =  dig_verif
    
    la   $t2, size
    lw   $s2, 0($t2)   				# $s2 = size
    
    la   $s3, encrypted
    
    # Calcular o deslocamento
    addi $s1, $s1, 1    				# dig_verif + 1
    add  $a1, $s0, $zero   		# $a1 = matricula
    add  $a0, $s1, $zero   		# $a0 = digito + 1
    jal  division          			# Chamar função de divisão
    add $t3, $v1, $zero 			# $t3 = resultado da divisão
    addi $t4, $zero, 0x1a  		# 26
    add  $a1, $t3, $zero    		# $a1 = resultado da divisão anterior
    add  $a0, $t4 , $zero 		# a0 = 26
    jal division		    			# Chamar função de divisão
    add $t5, $v0, $zero 			# $t5 = resultado do módulo
    
    # Descriptografar frase
    xor $t6, $t6, $t6           		# Índice inicial
    
loop:
    beq  $t6, $s2, end           		# Se o índice for igual ao tamanho, termina
    
    lw   $t7, 0($s3)             		# Carrega o byte encriptado atual
    sub  $t7, $t7, $t5           		# Subtrai o deslocamento
    
    # Verificar se precisamos "dar a volta" no alfabeto
    addi $t8, $zero, 0x61        		# Caractere 'a'
    bge  $t7, $t8, else    			# Se o caractere é >= 'a', não precisa dar a volta
    
    addi $t7, $t7, 0x1a          		# Caso contrário, soma 26
    
else:
    sw   $t7, 0($s3)             		# Armazena o caractere descriptografado
    addi $s3, $s3, 4             		# Avança para o próximo 
    addi $t6, $t6, 1             		# Incrementa o índice
    j    loop            				# Repete o loop

end:
    j end						# Acabou

#################################################################################################
###  Divisão serial  $a1/ $a0 -->   $v0--> resto    $v1 --> divisão
################################################################################################
division:
    lui   $t0, 0x8000       # máscara para isolar bit mais significativo
    addi  $t1, $zero, 32    # contador de iterações

    xor   $v0, $v0, $v0     # registrador P($v0)-A($v1) com  0 e o dividendo ($a1)
    add   $v1, $a1, $0

dloop:     
    and   $t2, $v1, $t0     # isola em t2 o bit mais significativo do registador 'A' ($v1)
    sll   $v0, $v0, 1       # desloca para a esquerda o registrado P-A
    sll   $v1, $v1, 1 

    beq   $t2, $0, di1    
    ori   $v0, $v0, 1       # coloca 1 no bit menos significativo do registador 'P'($v0)

di1:       
    sub   $t2, $v0, $a0     # subtrai 'P'($v0) do divisor ($a0)
    blt   $t2, $0, di2
    add   $v0, $t2, $0      # se a subtração deu positiva, 'P'($v0) recebe o valor da subtração
    ori   $v1, $v1, 1       # e 'A'($v1) recebe 1 no bit menos significativo

di2:       
    addi  $t1, $t1, -1      # decrementa o número de iterações 
    bne   $t1, $0, dloop 

    jr    $ra  

.data
matricula: .word 23102625
dig_verif: .word 3
size: .word 46
encrypted: .word 0x64, 0x71, 0x62, 0x65, 0x77, 0x79, 0x73, 0x71, 0x72, 0x79, 0x64, 0x71, 0x68, 0x79, 0x71, 0x71, 0x76, 0x71, 0x63, 0x79, 0x62, 0x79, 0x71, 0x75, 0x6a, 0x6b, 0x74, 0x65, 0x65, 0x6b, 0x64, 0x71, 0x74, 0x71, 0x62, 0x79, 0x6a, 0x75, 0x68, 0x71, 0x62, 0x63, 0x75, 0x64, 0x6a, 0x75

