
function hello()
    print("hello world")
end

function compute(number1,number2)
    local result = number1 + number2
    return result
end

local erpInventoryCoreService = _G.erpInventoryCoreService

function testSpringBean()
    if erpInventoryCoreService == nil
    then
        print("spring bean is nil")
    else
        print("spring bean is not nil")
        erpInventoryCoreService:print()
    end
end
