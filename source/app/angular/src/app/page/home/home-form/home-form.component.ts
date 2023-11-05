import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/shared/auth/auth.service';

@Component({
  selector: 'app-home-form',
  templateUrl: './home-form.component.html',
  styleUrls: ['./home-form.component.scss']
})
export class HomeFormComponent implements OnInit {
  
  constructor(
    private auth: AuthService
  ) {
    // Inicialize quaisquer dependências ou variáveis necessárias aqui
  }

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
  }

  logout(): void{
    this.auth.logout();
  }
}
