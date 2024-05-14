function search(type = 1) {
    fetch('/api/thietbi')
        .then(response => response.json())
        .then(data => {
            loadData(data)
        })
        .catch(error => console.error('Error:', error));

    function loadData(list) {
        const textSearch = document.querySelector('.form-control')
        const newList = []
        const listRem = []

        function convertString(str) {
            return str.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, '').replace(/[^a-z ]/g, 'd')
        }

        const value = convertString(textSearch.value);
        
        const bang = document.querySelector('.body_table')

        if(type == 1) {
            for(idx of list) {
                console.log(String(idx.maTB).includes(textSearch.value))
                if(String(idx.maTB).includes(textSearch.value)  || convertString(idx.tenTB).includes(value)) {
                    newList.push(idx)
                }
            }
            if(newList.length != 0) {
                bang.innerHTML = newList.map((item,idx) => `<tr>
                                    <td class="text-center">${idx + 1}</td>
                                    <td class="text-center">${item.maTB}</td>
                                    <td class="text-center">${item.tenTB}</td>
                                    <td class="text-center">${item.moTaTB}</td>
                                    <td class="text-center">
                                        <div class="d-flex justify-content-center">
                                        <input type="checkbox" class="form-check-input" id="check${idx + 1}" name="option${idx + 1}" value="">
                                        </div>
                                    </td>
                                </tr>`).join('')
            } else {
                bang.innerHTML =`<tr>
                                    <td colspan="5" class="text-center">Không tìm thấy tên thiết bị vui lòng thử lại</td>
                                </tr>`
            }
            document.querySelector('.list-rem').style.display = 'none'
        } else {
            const value1 = convertString(textSearch.value);
            for(idx of list) {
                if(convertString(idx.tenTB).includes(value)) {
                    if(!listRem.includes(idx.tenTB)) {
                        listRem.push(idx.tenTB);
                    }
                }
            }
            if(listRem.length > 0) {
                document.querySelector('.list-rem').style.display = 'block'
                document.querySelector('.list-rem').innerHTML = listRem.map(item => 
                    `<div class="item-rem" onclick="changeValue('${item}')">${item}</div>`).join('')
            } else {
                document.querySelector('.list-rem').style.display = 'none'
            }
        }
    }
}

function changeValue(text) {
    document.querySelector('.form-control').value = text
    document.querySelector('.list-rem').style.display = 'none'
}

document.body.addEventListener('click', () => {
    document.querySelector('.list-rem').style.display = 'none'
})
