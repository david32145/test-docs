const inputTextGroup = document.querySelectorAll('.input-text-group')

for(let i = 0; i < inputTextGroup.length; i++){
    const input = inputTextGroup[i].querySelector('input')
    const label = inputTextGroup[i].querySelector('label')

    input.onfocus = function(event){
        if(label.classList.contains('animate-text-input-label-out')){
            label.classList.replace('animate-text-input-label-out', 'animate-text-input-label-in')
        }else{
            label.classList.add('animate-text-input-label-in')
        }
    }

    input.onblur = function(event){
        if(input.value.trim() === ''){
            if(label.classList.contains('animate-text-input-label-in')){
                label.classList.replace('animate-text-input-label-in', 'animate-text-input-label-out')
            }else{
                label.classList.add('animate-text-input-label-out')
            }
        }
    }

    label.onanimationend = function(ani){
        
    }
}
