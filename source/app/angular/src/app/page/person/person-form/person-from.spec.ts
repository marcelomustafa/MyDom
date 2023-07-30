import { TestBed, ComponentFixture } from '@angular/core/testing';
import { PersonFormComponent } from './person-form.component'; 


describe('MeuComponente', () => {
  let fixture: ComponentFixture<PersonFormComponent>;
  let componente: PersonFormComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonFormComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(PersonFormComponent);
    componente = fixture.componentInstance;
  });

  it('deve calcular a soma corretamente', () => {
    const resultado = componente.calcularSoma(2, 3);
    expect(resultado).toBe(5);
  });
});
