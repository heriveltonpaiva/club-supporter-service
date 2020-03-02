package br.com.hpaiva.clubsupporterservice.queue;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Notification implements Serializable {

    private Object data;

}
