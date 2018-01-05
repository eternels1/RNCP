import { TestBed, inject } from '@angular/core/testing';

import { TodolisteRepositoryService } from './todoliste-repository.service';

describe('TodolisteRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TodolisteRepositoryService]
    });
  });

  it('should be created', inject([TodolisteRepositoryService], (service: TodolisteRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
