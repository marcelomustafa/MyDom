export class ToolUtils{

    private equalsIgnoringCaseAndSpace(text: any, other: any): boolean{
        return this.equalsIgnoringCase((text ? text : '').replace(' ',''), (other ? other : '').replace(' ',''));
    }
    
    private equalsIgnoringCase(text: any, other: any): boolean{
        return (text ? text : '').localeCompare((other ? other : ''), undefined, { sensitivity: 'base'}) === 0;
    }
    
}