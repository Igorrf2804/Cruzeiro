function calculaIdade(){
   var input = document.querySelector("#date");
   var dataNasc = input.value;
   var dataAtual = new Date();
   var anoAtual = dataAtual.getFullYear();
   var anoNascParts = dataNasc.split('-');
   var anoNasc =anoNascParts[0];
   var mesNasc =anoNascParts[1];
   var diaNasc =anoNascParts[2];
   var idade = anoAtual - anoNasc;
   var mesAtual = dataAtual.getMonth() + 1; 
   var diaAtual = dataAtual.getDay() + 1; 

   //Se mes atual for menor que o nascimento, nao fez aniversario ainda;  
   var numidade = document.getElementById('idade').value = idade;

    if(idade < 0 || idade > 110){
      window.alert("ATENÇÃO! Confira o campo idade!")
      var classtext = document.querySelector(".input-box-name");
      var classinput = document.querySelector(".input-box-number");
    }
    else {
      if(idade < 18){
        window.alert("ATENÇÃO! Menor de idade, não esqueça de informar o responsável!")
        var classtext = document.querySelector(".input-box-name");
        var classinput = document.querySelector(".input-box-number");
        classtext.classList.toggle("ativo");
        classinput.classList.toggle("ativo");
      }
  }
}