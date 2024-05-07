const list = [
    {
        MaTB:'1000001',
        TenTB: 'Micro',
        MoTaTB: 'Micro không dây MS2023',
    },
    {
        MaTB:'1000002',
        TenTB: 'Micro',
        MoTaTB: 'Micro không dây MS2024',
    },
    {
        MaTB:'1000003',
        TenTB: 'Bảng điện tử',
        MoTaTB: 'Bản điện tử trình chiếu',
    },
    {
        MaTB:'1000004',
        TenTB: 'a',
        MoTaTB: 'test',
    },
    {
        MaTB:'1000004',
        TenTB: 'a',
        MoTaTB: 'test',
    },
    {
        MaTB:'1000004',
        TenTB: 'a',
        MoTaTB: 'test',
    },
    {
        MaTB:'1000004',
        TenTB: 'a',
        MoTaTB: 'test',
    },
]

function search() {
    const textSearch = document.querySelector('.form-control')
    const newList = []

    function convertString(str) {
        return str.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, '').replace(/[^a-z ]/g, 'd')
    }

    const value = convertString(textSearch.value);
    
    for(idx of list) {
        console.log(idx.MaTB)
        console.log(value)
        if(idx.MaTB.includes(textSearch.value)  || convertString(idx.TenTB).includes(value)) {
            newList.push(idx)
        }
    }
    const bang = document.querySelector('.body_table')
    console.log(newList.length)
    
    if(newList.length != 0) {
        bang.innerHTML = newList.map((item,idx) => `<tr>
                            <td class="text-center">${idx + 1}</td>
                            <td class="text-center">${item.MaTB}</td>
                            <td class="text-center">${item.TenTB}</td>
                            <td class="text-center">${item.MaTB}</td>
                            <td class="text-center">còn</td>
                        </tr>`).join('')
    } else {
        bang.innerHTML =`<tr>
                            <td colspan="5" class="text-center">Không tìm thấy tên thiết bị vui lòng thử lại</td>
                        </tr>`
    }
}