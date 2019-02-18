import { User } from "./user";
import { Language } from "./language";
import { Platforms } from "./platforms";
import { Keyword } from "./keyword";


export class Student {
    idStudent: number;
    name: string;
    surname: string;
    phone: string;
    email: string;
    grade: string;
    thesis: string;
    avatar: string;
    cv: string;
    userEntity: User;
    languageEntities: Language[];
    platformEntities: Platforms[];
    keywordEntities: Keyword[];
}